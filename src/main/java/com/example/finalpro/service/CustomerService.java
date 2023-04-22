package com.example.finalpro.service;

import com.example.finalpro.dao.CustomerDAO;
import com.example.finalpro.db.DBManager;
import com.example.finalpro.entity.Customer;
import com.example.finalpro.vo.CustomerVO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Setter
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerDAO dao;

    public List<Customer> findAll(){return dao.findAll();}

    // 특정 회원의 정보를 출력하는 메소드
    // 1. main.html에서 회원의 선호하는 장르 cateid를 가져오기 위함
    public Customer findByCustid(String custid){
        if(dao.findById(custid).isPresent()){
            return dao.findById(custid).get();
        }
        return null;
    }

    public Optional<Customer> findCustomerByCustid(String custid){
        return dao.findById(custid);
    }

    // 고객 정보 수정
    public int updateCustomer(CustomerVO customer){
        String birth = customer.getBirth();
        String[] list_birth = birth.split("\\s");
        birth = list_birth[0];
        System.out.println(birth);
        customer.setBirth(birth);

        return DBManager.updateCustomer(customer);
    }

    // 고객 삭제
    public void deleteCustomer(String custid){
        dao.deleteById(custid);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("사용자 로그인 처리");
        System.out.println("username:" + username);
        Optional<Customer> obj = dao.findById(username);
        UserDetails user = null;
        if (obj.isPresent()) {
            Customer c = obj.get();
            user = User.builder().username(username).password(c.getPwd()).roles(c.getRole()).build();
            System.out.println(user.getAuthorities());
        } else {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    // 카카오 계정 토큰 발급
    public String getKakaoToken(String code){
        String access_token = "";
        String refresh_token = "";
        String client_id = "e0317fb32aeccf8761988099a515da12";
        String post_url = "http://localhost:8088/oauth/kakao";
        String request_url = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(request_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            // 1. setDoOutput이 뭐지
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            // 2. 이건 뭐지
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder sb = new StringBuilder();

            sb.append("grant_type=authorization_code");
            sb.append("&client_id="+client_id);
            sb.append("&redirect_uri="+post_url);
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = connection.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // gson 라이브러리를 통해 json 읽기
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            access_token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_token);
            System.out.println("refresh_token : " + refresh_token);
            br.close();
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return access_token;
    }

    public HashMap<String, String> getKakaoUser(String access_token){
        String request_url = "https://kapi.kakao.com/v2/user/me";
        HashMap<String, String> userInfo = new HashMap<>();

        try {
            URL url = new URL(request_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            // 1. setDoOutput이 뭐지
            // 2. 언제 어떤 메소드를 쓰는지 어떻게 알지
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer " + access_token);

            //결과 코드가 200이라면 성공
            int responseCode = connection.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // gson 라이브러리를 통해 json 읽기
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String nickname = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("profile").getAsJsonObject().get("nickname").getAsString();
            String email = "";
            if (hasEmail) {
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userInfo;
    }



}
