// 처음 시작시 화면의 사이즈 값을 가진다.

gogo();
$(".hi").css("pointer-events", "none");
$(".hi").addClass("load");
function isMobile() {
  return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(
    navigator.userAgent
  );
}

$(function () {
  var duration = 500;
  var $sidebar = $(".sidebar");
  var $notice = $(".notice");
  var $nono = $(".nono");
  var $notive_btn = $(".notice-btn");
  var overlay = $(".overlay");
  var $sidebarButton = $(".slidede").on("click", function () {
    $sidebar.toggleClass("open");
    if ($sidebar.hasClass("open")) {
      $sidebar.stop(true).animate({ left: "0px" }, duration, "easeOutSine");
      overlay.fadeIn();
    } else {
      $sidebar.stop(true).animate({ left: "-270px" }, duration, "easeInSine");
      overlay.fadeOut();
    }
  });

  $(".overlay").on("click", function () {
    $sidebar.toggleClass("open");
    if ($sidebar.hasClass("open")) {
      $sidebar.stop(true).animate({ left: "0px" }, duration, "easeOutSine");
      overlay.fadeIn();
    } else {
      $sidebar.stop(true).animate({ left: "-270px" }, duration, "easeInSine");
      overlay.fadeOut();
    }
  });

  $notive_btn.on("click", function () {
    $notice.css("display", "none");
    $nono.css("display", "none");
  });

  $nono.on("click", function () {
    $notice.css("display", "none");
    $nono.css("display", "none");
  });

  $sidebar.swipe({
    swipeStatus: function (
      event,
      phase,
      direction,
      distance,
      duration,
      fingers
    ) {
      if (phase == "move" && direction == "right") {
        return false;
      }
      if (phase == "move" && direction == "left") {
        if ($sidebar.hasClass("open")) {
          $sidebar.stop(true).animate({ left: "-270px" }, 500, "easeInSine");
          overlay.fadeOut();
        }
        $sidebar.removeClass("open");

        return false;
      }
    },
  });
});

// 창의 사이즈 변화가 일어났을 경우 실행된다.
// $(window).resize(function() {
//   // 처음 사이즈와 현재 사이즈가 변경된 경우
//   // 키보드가 올라온 경우
//   if ($(window).width() + $(window).height() != originalSize) {
//     $(".hohow").css("display", "none");
//   }
//   // 처음 사이즈와 현재 사이즈가 동일한 경우
//   // 키보드가 다시 내려간 경우
//   else {
//     $(".hohow").css("display", "block");
//   }
// });

var arrow_check = false;
var seq_check = "";
var use = true;
var cluster_check = true;
var cuMap = [];

if (arrow_check) {
  $(".o").css("color", "rgba(12, 92, 241, 0.801)");
} else {
  $(".o").css("color", "black");
}

// var map = null,
//     pano = null;

var map = null;

map = new naver.maps.Map(document.getElementById("map"), {
  useStyleMap: true,
  zoom: 17,
  center: new naver.maps.LatLng(35.158526, 129.062031),
});

var seoul = new naver.maps.LatLngBounds(
        new naver.maps.LatLng(37.42829747263545, 126.76620435615891),
        new naver.maps.LatLng(37.7010174173061, 127.18379493229875));

busan = new naver.maps.LatLng(35.179783, 129.074987),

daejeon = new naver.maps.LatLng(36.350500, 127.384862),

wonju = new naver.maps.LatLng(37.342071, 127.919624),

gwangju = new naver.maps.LatLng(37.429485, 127.255142),

itwill = new naver.maps.LatLng(35.158526, 129.062031)



$("#to-itwill").on("click", function(e) {
e.preventDefault();

map.panTo(itwill);
});


$("#to-daejeon").on("click", function(e) {
e.preventDefault();

map.panTo(daejeon);
});


$("#to-busan").on("click", function(e) {
e.preventDefault();

map.panTo(busan);
});

$("#to-gwangju").on("click", function(e) {
	e.preventDefault();

	map.panTo(gwangju);
	});

$("#to-wonju").on("click", function(e) {
	e.preventDefault();

	map.panTo(wonju);
	});

$("#to-seoul").on("click", function(e) {
e.preventDefault();

map.panToBounds(seoul);
});


var CurrentOverlay = function (options) {
  this._element = $(options.content);

  this.setPosition(options.position);
  this.setMap(options.map || null);
};

// CustomOverlay는 OverlayView를 상속받습니다.
CurrentOverlay.prototype = new naver.maps.OverlayView();

CurrentOverlay.prototype.constructor = CurrentOverlay;

CurrentOverlay.prototype.onAdd = function () {
  var overlayLayer = this.getPanes().floatPane;

  this._element.appendTo(overlayLayer);
};

CurrentOverlay.prototype.draw = function () {
  // 지도 객체가 설정되지 않았으면 draw 기능을 하지 않습니다.
  if (!this.getMap()) {
    return;
  }

  // projection 객체를 통해 LatLng 좌표를 화면 좌표로 변경합니다.
  var projection = this.getProjection(),
    position = this.getPosition();

  var pixelPosition = projection.fromCoordToOffset(position);

  this._element.css("left", pixelPosition.x);
  this._element.css("top", pixelPosition.y);
};

CurrentOverlay.prototype.onRemove = function () {
  this._element.remove();
  0 - [];
  // 이벤트 핸들러를 설정했다면 정리합니다.
  this._element.off();
};

CurrentOverlay.prototype.setPosition = function (position) {
  this._position = position;
  this.draw();
};

CurrentOverlay.prototype.getPosition = function () {
  return this._position;
};

if (mylat && mylng) {
  map.setZoom(13, false);
  map.panTo(new naver.maps.LatLng(mylat, mylng));
  if (use) {
    var current = new CurrentOverlay({
      position: new naver.maps.LatLng(mylat, mylng),
      content: $(
        '<img class="pulse" draggable="false" unselectable="on" src="https://ssl.pstatic.net/static/maps/m/pin_rd.png" alt="" style="margin: 0px;padding: 0px;  border: 0px solid transparent; display: block; user-select: none;  -webkit-user-drag: none;  box-sizing: content-box !important; max-width: none !important;max-height: none !important;min-width: 0px !important; min-height: 0px !important; position: absolute; cursor: pointer; width: 22px; height: 22px;left: 0px;top: 0px;">'
      ),
    });
    hi_check = true;
    if (hi_check) {
      $(".hi").addClass("active");
      map.panTo(new naver.maps.LatLng(mylat, mylng));
      cuMap.push(current);
      current.setMap(map);
      hi_check = false;
    } else {
      $("hi").removeClass("active");
      current = cuMap.shift();
      current.setMap(null);
      hi_check = true;
    }
  } else {
    alert("현재 위치 정보를 공유해주세요.");
  }
}

var tlat, tlng;

var markers = [],
  infoWindows = [],
  infos = [];

function gogo() {
  if (!!navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
  } else {
    alert("이 브라우저는 Geolocation를 지원하지 않습니다");
  }
}

// var map = new naver.maps.Map(document.getElementById("map"), {
//   useStyleMap: true,
//   zoom: 10,
//   center: new naver.maps.LatLng(37.510946, 126.810117)
// });

$(function () {
  // Geolocation API에 액세스할 수 있는지를 확인
  if (navigator.geolocation) {
    //위치 정보를 얻기
    navigator.geolocation.getCurrentPosition(function (pos) {
      // tlat = pos.coords.latitude;     // 위도
      // tlng = pos.coords.longitude; // 경도
    });
  } else {
    alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
  }
});

function errorCallback(error) {
  use = false;
}

function successCallback(position) {
  use = true;
  tlat = position.coords.latitude;
  tlng = position.coords.longitude;
  $(".hi").css("pointer-events", "all");
  $(".hi").removeClass("load");
}
function fn_dateTimeToFormatted(dt) {
  var min = 60 * 1000;
  var c = new Date();
  var d = dt;
  var minsAgo = Math.floor((c - d) / min);

  var result = {
    raw:
      (d.getMonth() + 1 > 9 ? "" : "0") +
      (d.getMonth() + 1) +
      "-" +
      (d.getDate() > 9 ? "" : "0") +
      d.getDate() +
      " " +
      (d.getHours() > 9 ? "" : "0") +
      d.getHours() +
      ":" +
      (d.getMinutes() > 9 ? "" : "0") +
      d.getMinutes(),
    formatted: "",
  };

  if (!english) {
    if (minsAgo == 0) {
      result.formatted = "방금 전";
    } else if (minsAgo < 60) {
      // 1시간 내
      result.formatted = minsAgo + "분 전";
    } else if (minsAgo < 60 * 24) {
      // 하루 내
      result.formatted = Math.floor(minsAgo / 60) + "시간 전";
    } else {
      // 하루 이상
      result.formatted = Math.floor(minsAgo / 60 / 24) + "일 전";
    }
  } else {
    if (minsAgo == 0) {
      result.formatted = "just before";
    } else if (minsAgo < 60) {
      // 1시간 내
      result.formatted = minsAgo + "minutes ago";
    } else if (minsAgo < 60 * 24) {
      // 하루 내
      result.formatted = Math.floor(minsAgo / 60) + "hour ago";
    } else {
      // 하루 이상
      result.formatted = Math.floor(minsAgo / 60 / 24) + "day ago";
    }
  }
  return result.formatted;
}

var select = 0;
let $notice_content = $(".notice-content");
let class_type = "";
let class_name = "";

$("#notice").click(function () {
  $(".notice-item").remove();
  notice.forEach(function (n) {
    if (n.type == "notice") {
      let result = "";
      if (n.date) {
        let date = n.date;
        result = fn_dateTimeToFormatted(date);
      }
      class_type = "noti";
      class_name = "공지";
      $notice_content.append(
        '<div class="notice-item"><div class="tag-wrap"><div class="tag ' +
          class_type +
          '">' +
          class_name +
          "</div><div class='tag-date'>" +
          result +
          "</div></div>" +
          n.content +
          "</div>"
      );
    }
  });
});

$("#speed").click(function () {
  $(".notice-item").remove();
  notice.forEach(function (n) {
    if (n.type == "speed") {
      let result = "";
      if (n.date) {
        let date = n.date;
        result = fn_dateTimeToFormatted(date);
      }
      class_type = "speed";
      class_name = "속보";
      $notice_content.append(
        '<div class="notice-item"><div class="tag-wrap"><div class="tag ' +
          class_type +
          '">' +
          class_name +
          "</div><div class='tag-date'>" +
          result +
          "</div></div>" +
          n.content +
          "</div>"
      );
    }
  });
});



$(".loading").css("display", "none");

var CustomOverlay = function (options) {
  this._element = $(options.content);

  this.setPosition(options.position);
  this.setMap(options.map || null);
};

// CustomOverlay는 OverlayView를 상속받습니다.
CustomOverlay.prototype = new naver.maps.OverlayView();

CustomOverlay.prototype.constructor = CustomOverlay;

CustomOverlay.prototype.onAdd = function () {
  var overlayLayer = this.getPanes().floatPane;

  this._element.appendTo(overlayLayer);
};

CustomOverlay.prototype.draw = function () {
  // 지도 객체가 설정되지 않았으면 draw 기능을 하지 않습니다.
  if (!this.getMap()) {
    return;
  }

  // projection 객체를 통해 LatLng 좌표를 화면 좌표로 변경합니다.
  var projection = this.getProjection(),
    position = this.getPosition();

  var pixelPosition = projection.fromCoordToOffset(position);

  this._element.css("left", pixelPosition.x + 15);
  this._element.css("top", pixelPosition.y - 31);
};

CustomOverlay.prototype.onRemove = function () {
  this._element.remove();

  // 이벤트 핸들러를 설정했다면 정리합니다.
  this._element.off();
};

CustomOverlay.prototype.setPosition = function (position) {
  this._position = position;
  this.draw();
};

CustomOverlay.prototype.getPosition = function () {
  return this._position;
};

// 현재위치
var $up = $(".up");
var ye = true;
$up.click(function () {
  $(".item-wa").slideToggle(function () {
    if (ye) {
      $up.attr("class", "up glyphicon glyphicon-chevron-up");
      ye = false;
    } else {
      $up.attr("class", "up glyphicon glyphicon-chevron-down");
      ye = true;
    }
  });
});

var jo = true;
$arrow = $(".arrow");
$arrow.click(function () {
  if (jo) {
    $arrow.attr("class", "arrow glyphicon glyphicon-chevron-up");
    jo = false;
  } else {
    $arrow.attr("class", "arrow glyphicon glyphicon-chevron-down");
    jo = true;
  }
  $arrow.slideToggle(function () {});
});

var leng = position.length;
// var positionReverse = position.reverse();
// var itemwahtml = "";

// $.each(positionReverse, function(index, item) {
//   if (item.title == "제주도 중국인 관광객") {
//     itemwahtml +=
//       '<button class="wawa" id="' +
//       index +
//       '"><span style="color:' +
//       item.color +
//       ';">제주도</span></button>';
//   } else if (item.solo) {
//   } else if (item.name) {
//     itemwahtml +=
//       '<button class="wawa" id="' +
//       index +
//       '"><span style="color:' +
//       item.color +
//       ';">' +
//       item.name +
//       "</span></button>";
//   } else {
//     itemwahtml +=
//       '<button class="wawa" id="' +
//       index +
//       '">' +
//       '<div style="color:rgb(78, 210, 142);font-size:11px;">' +
//       (item.status == "완치" ? "완치" : "") +
//       "</div>" +
//       '<span style="color:' +
//       "rgb(75, 75, 74)" +
//       ';">' +
//       (leng - index - 1) +
//       "번째</span></button>";
//   }
// });

// $(".item-wa").html(itemwahtml);

var htmlMarker1 = {
    content:
      '<div class="pul" style="cursor:pointer;width:25px;height:25px;line-height:26px;font-size:10px;border-radius:50%;color:white;text-align:center;font-weight:bold;background:rgba(255, 149, 79, 0.75);background-size:contain;"></div>',
    size: N.Size(40, 40),
    anchor: N.Point(20, 20),
  },
  htmlMarker2 = {
    content:
      '<div class="pul" style="cursor:pointer;width:30px;height:30px;line-height:31px;font-size:10px;border-radius:50%;color:white;text-align:center;font-weight:bold;background:rgba(255, 149, 79, 0.75);background-size:contain;"></div>',
    size: N.Size(40, 40),
    anchor: N.Point(20, 20),
  },
  htmlMarker3 = {
    content:
      '<div class="pul" style="cursor:pointer;width:35px;height:35px;line-height:36px;font-size:10px;border-radius:50%;color:white;text-align:center;font-weight:bold;background:rgba(255, 149, 79, 0.75);background-size:contain;"></div>',
    size: N.Size(40, 40),
    anchor: N.Point(20, 20),
  };

let lengco;
var markerList = [];
var polylineList = [];
var coco = 1;
var today = new Date();
var nine = [];
let distance;

for (var i in position) {
  arr = [];
  var mark = [];
  let full;
 
  name = position[i].name
  full = position[i].full;

 
    let latlng = new naver.maps.LatLng(position[i].wgs84Lat, position[i].wgs84Lon);
    arr.push(latlng);

    var content = document.createElement("div");
    content.className = "wrap";

    var info = document.createElement("div");
    info.className = "info";
    content.appendChild(info);

    var title = document.createElement("div");
    title.className = "title";
    title.innerHTML = full ? full : position[i]["title"];
    info.appendChild(title);

    var body = document.createElement("div");
    body.className = "body";
    body.innerHTML =
      position[i]["address"];
    info.appendChild(body);

    marker = new naver.maps.Marker({
      position: latlng,
      map: map,
      icon: {
        content:
          // i == ii - 1 && coco !== leng && !position[i].solo
          // ? "<div style='opacity:0.9;border:1px solid black;border-radius:50%;height:25px;width:25px;background:red;'><span style='position: absolute;left: 0px;top: 1.8px;line-height: 0.9;color:white;font-size:25px;opacity:1;' class='glyphicon glyphicon-plus-sign'></span></div>"
          "<div style='border: 1px solid black;color:white;opacity:0.8;display:flex;justify-content:center;align-items:center; width: 26px;height: 26px;border-radius: 50%;background-color: green" +
          ";'>" +
          "" +
          "</div>",
        anchor:
          // i == ii - 1
          // ? new naver.maps.Point(12, 12)
          new naver.maps.Point(13, 13),
      },
    });

    marker.address_english = "asd";
    marker.address = "asd";
    marker.month = "asd";
    marker.day = "asd";
    marker.full = full ? full : "asd";
    marker.set("seq", i);

    var infowindow = new naver.maps.InfoWindow({
      content: content,
      backgroundColor: "#00ff0000",
      borderColor: "#00ff0000",
      anchorSize: new naver.maps.Size(0, 0),
      anchorColor: "white",
      pixelOffset: new naver.maps.Point(0, -20),
    });

    // 마우스 올릴때

    mark.push(marker);
    markers.push(marker);
    infoWindows.push(infowindow);
    // infos.push(overlay);
    markerList.push(mark);
    icon = null;
    marker = null;
    co = 0;
  
}

var nine_check = false;
var nine_co = 0;
// function ninenine() {
//   if (!nine_check) {
//     for (var i in position) {
//       let full;
//       if (position[i].latlng !== "") {
//         if (position[i].name) {
//           name = position[i].name;
//           full = position[i].full;
//           lengco = "";
//         } else {
//           lengco = leng - coco;
//         }
//         let latlngreplace = position[i].latlng.replace(/(\s*)/g, "");
//         let sp = latlngreplace.split(",");
//         let latlng = new naver.maps.LatLng(sp[0], sp[1]);
//         arr.push(latlng);

//         // var info_content = document.createElement("div");
//         // info_content.className = "info_content";
//         // info_content.innerHTML = full ? full : position[i]["title"];

//         var content = document.createElement("div");
//         content.className = "wrap";

//         var info = document.createElement("div");
//         info.className = "info";
//         content.appendChild(info);

//         var title = document.createElement("div");
//         title.className = "title";
//         title.innerHTML = full ? full : position[i]["title"];
//         info.appendChild(title);

//         var body = document.createElement("div");
//         body.className = "body";
//         body.innerHTML =
//           position[i]["month"].toString() +
//           "/" +
//           position[i]["day"].toString() +
//           " " +
//           position[i]["address"];
//         info.appendChild(body);

//         let cocolor = "";

//         if (position[i].month == 1) {
//           cocolor = "rgba(26, 165, 49, 0.932)";
//         } else if (position[i].month == 2) {
//           let distance = today.getDate() - position[i].day;
//           if (distance >= 10) {
//             cocolor = "rgba(26, 165, 49, 0.932)";
//           } else if (distance >= 4 && distance < 10) {
//             cocolor = "rgba(26, 165, 49, 0.932)";
//             continue;
//           } else if (distance >= 2 && distance < 4) {
//             cocolor = "rgba(255, 196, 0, 0.9)";
//             continue;
//           } else {
//             cocolor = "red";
//             continue;
//           }
//         }

//         marker = new naver.maps.Marker({
//           position: latlng,
//           map: map,
//           icon: {
//             content:
//               // i == ii - 1 && coco !== leng && !position[i].solo
//               // ? "<div style='opacity:0.9;border:1px solid black;border-radius:50%;height:25px;width:25px;background:red;'><span style='position: absolute;left: 0px;top: 1.8px;line-height: 0.9;color:white;font-size:25px;opacity:1;' class='glyphicon glyphicon-plus-sign'></span></div>"
//               "<div style='border: 1px solid black;color:white;opacity:0.8;display:flex;justify-content:center;align-items:center; width: 26px;height: 26px;border-radius: 50%;background-color:" +
//               cocolor +
//               ";'>" +
//               "" +
//               "</div>",
//             anchor:
//               // i == ii - 1
//               // ? new naver.maps.Point(12, 12)
//               new naver.maps.Point(13, 13)
//           }
//         });

//         marker.address_english = position[i].address_english;
//         marker.add = "qweq";

//         marker.set("seq", i);

//         // var info = new naver.maps.InfoWindow({
//         //   content: info_content,
//         //   backgroundColor: "#00ff0000",
//         //   borderColor: "#00ff0000",
//         //   anchorSkew: false,
//         //   disableAnchor: true,
//         //   pixelOffset: new naver.maps.Point(1210, 120),
//         //   anchorColor: "#00ff0000"
//         // });

//         var infowindow = new naver.maps.InfoWindow({
//           content: content,
//           backgroundColor: "#00ff0000",
//           borderColor: "#00ff0000",
//           anchorSize: new naver.maps.Size(0, 0),
//           anchorColor: "white",
//           pixelOffset: new naver.maps.Point(0, -20)
//         });

//         // 마우스 올릴때
//         mark.push(marker);
//         markers.push(marker);
//         infoWindows.push(infowindow);
//         // infos.push(overlay);
//         markerList.push(mark);
//         icon = null;
//         marker = null;
//         co = 0;
//       }
//     }
//     nine_check = true;
//     nine_co += 1;
//   } else if (nine_co !== 0 && nine_check == true) {
//     markers.forEach(function(n) {
//       if (n.nine) {
//         n.setMap(null);
//       }
//     });
//     nine_check = false;
//   } else if (nine_co !== 0 && nine_check == false) {
//     markers.forEach(function(n) {
//       if (n.nine) {
//         n.setMap(map);
//       }
//     });
//     nine_check = true;
//   }

//   markerClustering.setMarkers(markers);
//   for (var i = 0, ii = markers.length; i < ii; i++) {
//     naver.maps.Event.addListener(map, "click", ClickMap(i));
//     naver.maps.Event.addListener(markers[i], "click", getClickHandler(i));
//     // naver.maps.Event.addListener(markers[i], "mouseover", MouseOverHandler(i));
//     // naver.maps.Event.addListener(markers[i], "mouseout", MouseOutHandler(i));
//   }
// }

// let pp = new naver.maps.Polyline({
//   map: map,
//   path: arr,
//   endIcon: 1,
//   strokeColor: arrow_color,
//   strokeStyle: "solid",
//   strokeOpacity: 1,
//   strokeWeight: 1,
//   strokeLineJoin: "round"
// });

var marker_select;

for (var i = 0, ii = markers.length; i < ii; i++) {
  naver.maps.Event.addListener(map, "click", ClickMap(i));
  naver.maps.Event.addListener(markers[i], "click", getClickHandler(i));
  // naver.maps.Event.addListener(markers[i], "mouseover", MouseOverHandler(i));
  // naver.maps.Event.addListener(markers[i], "mouseout", MouseOutHandler(i));
}

function makeListener(seq) {
  return function (e) {
    var infoWindow = infoWindows[seq];
    infoWindow.close();
  };
}

function loadListener(seq) {
  return function (e) {
    // naver.maps.Service.reverseGeocode({
    //             coords: new naver.maps.LatLng(37.3595316, 127.1052133),
    //         }, function(status, response) {
    //             if (status !== naver.maps.Service.Status.OK) {
    //                 return alert('Something wrong!');
    //             }

    //             var result = response.v2, // 검색 결과의 컨테이너
    //                 items = result.results; // 검색 결과의 배열
    //             alert(items);
    //             // do Something
    // });
    window.location.href =
      "https://map.naver.com/?etext=" +
      position[seq]["title"] +
      "&stext=현재위치&elng=" +
      position[seq]["lng"] +
      "&menu=route&elat=" +
      position[seq]["lat"] +
      "&pathType=1&slng=" +
      tlng +
      "&slat=" +
      tlat;
  };
}
if (cluster_check) {
  var markerClustering = new MarkerClustering({
    minClusterSize: 4,
    maxZoom: 12,
    map: map,
    markers: markers,
    disableClickZoom: false,
    gridSize: 100,
    icons: [htmlMarker1, htmlMarker2, htmlMarker3],
    indexGenerator: [10, 100, 200],
    stylingFunction: function (clusterMarker, count) {
      $(clusterMarker.getElement()).find("div:first-child").text(count);
    },
  });
}

function ClickMap(seq) {
  return function (e) {
    if (seq == marker_select) {
      $("input").blur();
      select = seq;
      var infoWindow = infoWindows[seq];
      infoWindow.close();
    }
  };
}

// console.log(lat);

// function MouseOverHandler(seq) {
//   return function(e) {
//     var marker = markers[seq],
//       overlay = infos[seq];
//     overlay.setMap(map);
//   };
// }

// function MouseOutHandler(seq) {
//   return function(e) {
//     var marker = markers[seq],
//       overlay = infos[seq];

//     overlay.setMap(null);
//   };
// }

function onMouseOver(e) {
  var marker = e.overlay,
    seq = marker.get("seq");
  var infoWindow = infoWindows[seq];
}

function getClickHandler(seq) {
  return function (e) {
    $("input").blur();
    marker_select = seq;
    select = seq;
    var marker = markers[seq],
      infoWindow = infoWindows[seq];
    if (infoWindow.getMap()) {
      infoWindow.close();
    } else {
      infoWindow.open(map, marker);
    }
  };
}

function onMouseOut(e) {
  var marker = e.overlay,
    seq = marker.get("seq");
  infowindow.close();
}
var id_check = -1;

var dash_check = true;
var $dada = $(".dada");
$dada.click(function () {
  if (dash_check) {
    $dada.attr("class", "dada glyphicon glyphicon-chevron-up");
    dash_check = false;
  } else {
    $dada.attr("class", "dada glyphicon glyphicon-chevron-down");
    dash_check = true;
  }
  $(".dash").slideToggle(function () {
    $dada.slideDown();
  });
  $(".dash_back").css("display", "none");
});

$(".caca").click(function () {
  $(".dashgo").css("display", "none");
  $(".dada").attr("class", "dada glyphicon glyphicon-chevron-down");
  $(".dash").slideToggle();
});

var pulse_arr = [];

$(".wawa").click(function () {
  id_check = $(this).attr("id");
  infoWindows[select].close();
  dashUp(id_check);

});

$(".arrow-box").click(function () {
  $(".notice").css("display", "flex");
  $(".nono").css("display", "block");
});

$(".dash-item").on("click", function () {
  $(this).toggleClass("back-red");
});

var hi_check = true;
$(".hi").click(function () {
  if (use) {
    var current = new CurrentOverlay({
      position: new naver.maps.LatLng(tlat, tlng),
      content: $(
        '<img class="pulse" draggable="false" unselectable="on" src="https://ssl.pstatic.net/static/maps/m/pin_rd.png" alt="" style="margin: 0px;padding: 0px;  border: 0px solid transparent; display: block; user-select: none;  -webkit-user-drag: none;  box-sizing: content-box !important; max-width: none !important;max-height: none !important;min-width: 0px !important; min-height: 0px !important; position: absolute; cursor: pointer; width: 22px; height: 22px;left: 0px;top: 0px;">'
      ),
    });
    if (hi_check && cuMap.length == 0) {
      $(this).addClass("active");
      map.panTo(new naver.maps.LatLng(tlat, tlng));
      cuMap.push(current);
      current.setMap(map);
      hi_check = false;
    } else {
      $(this).removeClass("active");

      current = cuMap.shift();
      current.setMap(null);
      hi_check = true;
    }
  } else {
    alert("현재 위치 정보를 공유해주세요.");
  }
});

var filter = "win16|win32|win64|mac";

if (navigator.platform) {
  if (filter.indexOf(navigator.platform.toLowerCase()) < 0) {
  } else {
    $(".wa").hover(
      function () {
        $(".arrow_box").fadeIn(200);
      },
      function () {
        $(".arrow_box").fadeOut(200);
      }
    );
  }
}

function pulse(latlng, hospital) {
  if (pulse_arr.length == 0) {
    marker = new naver.maps.Marker({
      position: latlng,
      map: map,
      icon: {
        content:
          hospital == true
            ? "<div class='pulse' style='opacity:0.9;border-radius:50%;height:25px;width:25px;'></div>"
            : "<div class='pulse' style='opacity:0.9;border-radius:50%;height:19px;width:19px;'></div>",
        anchor:
          hospital == true
            ? new naver.maps.Point(12, 12)
            : new naver.maps.Point(10, 10),
      },
    });
    marker.setZIndex(-1);
    pulse_arr.push(marker);
  } else {
    marker = new naver.maps.Marker({
      position: latlng,
      map: map,
      icon: {
        content:
          hospital == true
            ? "<div class='pulse' style='opacity:0.9;border-radius:50%;height:25px;width:25px;'></div>"
            : "<div class='pulse' style='opacity:0.9;border-radius:50%;height:19px;width:19px;'></div>",
        anchor:
          hospital == true
            ? new naver.maps.Point(12, 12)
            : new naver.maps.Point(10, 10),
      },
    });
    marker.setZIndex(-1);
    pulse_arr.push(marker);
    let pre_pulse = pulse_arr.splice(0, 1);
    pre_pulse[0].setMap(null);
  }
}

function aff(latlng) {
  let tp = latlng.split(",");
  const lat = tp[0];
  const lng = tp[1];
  const naver2 = new naver.maps.LatLng(lat, lng);
  // let poso = position[id_check].length - 1;
  // if (i == poso) {
  // pulse(position[id_check][i].latlng, true);
  // } else {
  // pulse(naver2, false);
  // }
  // map.setZoom(13, false);
  map.panTo(naver2);
}

$(".search_back").click(function () {
  $(".search-wrap").css("display", "none");
});

var ps = new kakao.maps.services.Places();

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
  $(".search_content_wrap").remove();
  $(".searchInput2").focus();

  if (status === kakao.maps.services.Status.OK) {
    // let latlng = new kakao.maps.LatLng(data[0].y, data[0].x);
    // map.setZoom(13, false);
    // map.panTo(latlng);
    // var bounds = map.getBounds();
    // region(bounds);
    // region(map.getCenter());
    let $search_wrap = $(".search_result_wrap");
    let latlng;
    data.forEach(function (n) {
      latlng = n.y + "," + n.x;
      $search_wrap.append(
        "<div id='" +
          n.y +
          "," +
          n.x +
          "' class='search_content_wrap'><div class='search_title_wrap'><div class='search_title'>" +
          n.place_name +
          "</div><div class='search_group'>" +
          n.category_group_name +
          "</div></div><div class='search_address'>" +
          n.address_name +
          "</div></div>"
      );
    });
  } else {
    alert("검색결과가 없습니다");
  }
}
var nine_check = false;
$(".wa2").click(function () {
  // ninenine();
});

$(document).on("click", ".search_content_wrap", function () {
  var pcode = $(this).attr("id"); //이거는 해당 element의 id value값을 가져오는것.
  let result = pcode.split(",");
  let latlng = new naver.maps.LatLng(result[0], result[1]);
  $(".search-wrap").css("display", "none");
  search(latlng);
});

var search_arr = [];

$(".search").click(function () {
  // ninenine();
  // $(".searchInput2").val("");
  // $(".searchInput2").focus();
  $(".search-wrap").css("display", "block");
  // $(".search_content_wrap").remove();
});

// $(".search_content_wrap").click(function() {
//   console.log("Eqwe");
//   alert("ee");
// });

var search_arr = [];

function search(latlng) {
  console.log(latlng);
  map.panTo(latlng);
  console.log("qweq");
  // var bounds = map.getBounds();
  // region(bounds);
  // region(map.getCenter());
  if (search_arr.length == 0) {
    marker = new naver.maps.Marker({
      position: latlng,
      map: map,
      zIndex: 12312313,
    });
    // marker.setZIndex(1000);
    search_arr.push(marker);
    // map.setZoom(16, true);
  } else {
    marker = new naver.maps.Marker({
      position: latlng,
      map: map,
      zIndex: 12312313,
    });
    // marker.setZIndex(1000);
    search_arr.push(marker);
    let pre_search = search_arr.splice(0, 1);
    pre_search[0].setMap(null);
    // map.setZoom(16, true);
  }
}

// function search(latlng) {
//   if (search_arr.length == 0) {
//     marker = new naver.maps.Marker({
//       position: latlng,
//       map: map
//     });
//     marker.setZIndex(1000);
//     search_arr.push(marker);
//     // map.setZoom(16, true);
//   } else {
//     marker = new naver.maps.Marker({
//       position: latlng,
//       map: map
//     });
//     marker.setZIndex(1000);
//     search_arr.push(marker);
//     let pre_search = search_arr.splice(0, 1);
//     pre_search[0].setMap(null);
//     // map.setZoom(16, true);
//   }
// }

$(".searchInput2").on("keydown", function (e) {
  var keyCode = e.which;
  if (keyCode === 13) {
    // Enter Key
    ps.keywordSearch($(".searchInput2").val(), placesSearchCB);
    $("input").blur();
  }
});

$(".sese").on("click", function () {
  ps.keywordSearch($(".searchInput2").val(), placesSearchCB);
  $("input").blur();
});

var HOME_PATH = "https://navermaps.github.io/maps.js/docs",
  urlPrefix = HOME_PATH + "/data/region",
  urlSuffix = ".json",
  regionGeoJson = [],
  loadCount = 0;

naver.maps.Event.once(map, "init_stylemap", function () {
  for (var i = 1; i < 18; i++) {
    var keyword = i + "";
    if (keyword.length === 1) {
      keyword = "0" + keyword;
    }
    $.ajax({
      url: urlPrefix + keyword + urlSuffix,
      success: (function (idx) {
        return function (geojson) {
          regionGeoJson[idx] = geojson;

          loadCount++;

          if (loadCount === 17) {
            startDataLayer();
          }
        };
      })(i - 1),
    });
  }
});

var tooltip = $(
  '<div style="position:absolute;z-index:1000;padding:5px 10px;background-color:#fff;border:solid 2px #000;font-size:14px;pointer-events:none;display:none;"></div>'
);

tooltip.appendTo(map.getPanes().floatPane);
var clcl = [];

function startDataLayer() {
  map.data.setStyle(function (feature) {
    var styleOptions = {
      fillColor: "rgba(226, 167, 100, 0.767)",
      fillOpacity: 0.5,
      strokeColor: "rgba(107, 100, 100, 0.767)",
      strokeWeight: 0,
      strokeOpacity: 0.4,
    };

    if (feature.getProperty("focus")) {
      // styleOptions.fillOpacity = 0.6;
      // styleOptions.fillColor = "#0f0";
      styleOptions.strokeColor = "rgba(184, 184, 184, 0.733)";
      styleOptions.strokeWeight = 2;
      styleOptions.strokeOpacity = 1;
    }

    return styleOptions;
  });


}

var english = false;

function dashUp(regionName) {
  var people;
  var en;
  var source = "질병관리본부";
  var date = "5.29";
  var time = "00";
  switch (regionName) {
    case "서울특별시":
      obj = regionDash("서울");
      people = 846;
      en = "Seoul";
      break;
    case "부산광역시":
      obj = regionDash("부산");
      people = 145;
      en = "Busan";
      break;
    case "대구광역시":
      obj = regionDash("대구");
      en = "Daegu";
      people = 6880;
      break;
    case "인천광역시":
      obj = regionDash("인천");
      en = "Incheon";
      people = 198;
      break;
    case "광주광역시":
      obj = regionDash("광주");
      en = "Gwangiu";
      people = 30;
      break;
    case "대전광역시":
      obj = regionDash("대전");
      en = "Daejeon";
      people = 45;
      break;
    case "울산광역시":
      obj = regionDash("울산");
      en = "Ulsan";
      people = 50;
      break;
    case "세종특별자치시":
      obj = regionDash("세종특별자치시");
      en = "Sejong";
      people = 47;
      break;
    case "경기도":
      obj = regionDash("경기");
      en = "Gyeonggi";
      people = 815;
      break;
    case "강원도":
      obj = regionDash("강원");
      en = "Gangwon";
      people = 56;
      break;
    case "충청북도":
      obj = regionDash("충북");
      en = "Chungbuk";
      people = 60;
      break;
    case "충청남도":
      obj = regionDash("충남");
      en = "Chungnam";
      people = 146;
      break;
    case "전라북도":
      obj = regionDash("전북");
      en = "Jeonbuk";
      people = 21;
      break;
    case "전라남도":
      obj = regionDash("전남");
      en = "Jeonnam";
      people = 18;
      break;
    case "경상북도":
      obj = regionDash("경북");
      en = "Gyeongbuk";
      people = 1379;
      break;
    case "경상남도":
      obj = regionDash("경남");
      en = "Gyeongnam";
      people = 123;
      break;
    case "제주특별자치도":
      obj = regionDash("제주특별자치도");
      en = "Jeju";
      people = 14;
      break;
    default:
      people = 0;
      break;
  }

  objectDash(obj);

  $(".dash").slideDown();
}


