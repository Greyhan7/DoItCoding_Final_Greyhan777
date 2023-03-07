// 네비게이션 바에서 클릭하면 카테고리별 목록으로 넘어가도록
$(function (){
var cate_current = "";
var cate_future = "";
var cate_past = "";

for (var i = 1; i <=4; i++) {
    cate_current = '#cate'+i+"_current";
    cate_future = '#cate'+i+"_future";
    cate_past = '#cate'+i+"_past";

    $(cate_current).click(function(e){
        var target = this.id;
        var cateid_num = $("#"+target).attr("cateid");
        var time_num = $("#"+target).attr("time");

        time = time_num;
        cateid = cateid_num;
        console.log(cateid);

        sessionStorage.setItem("time", time);
        sessionStorage.setItem("cateid", cateid);

        var url = "/category?time="+time+"?cateid="+cateid;
        location.replace(url);
    });

    $(cate_future).click(function(e){
        var target = this.id;
        var cateid_num = $("#"+target).attr("cateid");
        var time_num = $("#"+target).attr("time");

        time = time_num;
        cateid = cateid_num;
        console.log(cateid);

        sessionStorage.setItem("time", time);
        sessionStorage.setItem("cateid", cateid);

        var url = "/category?time="+time+"?cateid="+cateid;
        location.replace(url);
    });

    $(cate_past).click(function(e){
        var target = this.id;
        var cateid_num = $("#"+target).attr("cateid");
        var time_num = $("#"+target).attr("time");

        time = time_num;
        cateid = cateid_num;
        console.log(cateid);

        sessionStorage.setItem("time", time);
        sessionStorage.setItem("cateid", cateid);

        var url = "/category?time="+time+"?cateid="+cateid;
        location.replace(url);
        });
    }
    // 검색하면 결과가 나오도록
    var searchKeyword = function(f){
        $("#search_txt").keydown(function(e){ // 엔터 누르면 검색되도록 (아직 구현 X)
            if(e.which == 13){
                var keyword = $("#search_txt").val();
                sessionStorage.setItem("keyword", keyword);
                var url = "search?keyword="+keyword;
                location.replace(url);
            }
        });

        $("#search_btn").click(function(){ // 버튼 누르면 검색됨
            var keyword = $("#search_txt").val();
            sessionStorage.setItem("keyword", keyword);
            var url = "/search?keyword="+keyword;
            location.href=url;
        });
    }// 검색 end

    searchKeyword();

})