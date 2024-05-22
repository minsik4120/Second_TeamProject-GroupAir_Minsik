var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(36.5, 127.5), // 지도의 중심좌표
        level: 14 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

var geocoder = new kakao.maps.services.Geocoder();

var toArea, fromArea;

geocoder.addressSearch('[[${airplaneDto.toArea}]]', function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
        toArea = new kakao.maps.LatLng(result[0].y, result[0].x);
        var customOverlay = new kakao.maps.CustomOverlay({
            map: map,
            position: toArea,
            content: '<div class="custom-marker">출발지</div>',
            yAnchor: 1
        });
        if (fromArea) {
            drawLine();
        }
    }
});

geocoder.addressSearch('[[${airplaneDto.fromArea}]]', function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
        fromArea = new kakao.maps.LatLng(result[0].y, result[0].x);
        var customOverlay = new kakao.maps.CustomOverlay({
            map: map,
            position: fromArea,
            content: '<div class="custom-marker">도착지</div>',
            yAnchor: 1
        });
        if (toArea) {
            drawLine();
        }
    }
});

function drawLine() {
    var linePath = [
        toArea,
        fromArea
    ];

    var polyline = new kakao.maps.Polyline({
        path: linePath, // 선을 구성하는 좌표배열입니다
        strokeWeight: 5, // 선의 두께입니다
        strokeColor: 'red', // 선의 색깔입니다
        strokeOpacity: 0.8, // 선의 불투명도입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'dot' // 선의 스타일입니다
    });

    polyline.setMap(map);

    var distance = Math.round(polyline.getLength() / 1000) + 'km'; // 선의 총 거리를 계산합니다

    var midPoint = new kakao.maps.LatLng(
        (toArea.getLat() + fromArea.getLat()) / 2,
        (toArea.getLng() + fromArea.getLng()) / 2
    );

    var customOverlay = new kakao.maps.CustomOverlay({
        position: midPoint,
        content: '<div class="infowindow">거리: ' + distance + '</div>'
    });

    customOverlay.setMap(map);

    var bounds = new kakao.maps.LatLngBounds();
    bounds.extend(toArea);
    bounds.extend(fromArea);

    // 출발지와 도착지가 화면에 딱 맞도록 지도를 조절합니다.
    map.setBounds(bounds);

    map.setDraggable(false);
    map.setZoomable(false);
}