<script>
    document.addEventListener("DOMContentLoaded", function() {
        // 현재 페이지의 URL 가져오기
        var currentPageUrl = window.location.href;

        // 모든 링크에 대해 반복
        document.querySelectorAll('.de_left ul a').forEach(function(link) {
            // 링크의 href 속성 값 가져오기
            var linkUrl = link.getAttribute('href');

            // 만약 현재 페이지와 링크의 URL이 같다면
            if (currentPageUrl === linkUrl) {
                // 방문 중인 페이지에 해당하는 링크에 스타일 적용
                link.style.backgroundColor = 'blue'; // 원하는 스타일로 변경
                link.style.color = 'white'; // 텍스트 색상 변경
            }
        });
    });