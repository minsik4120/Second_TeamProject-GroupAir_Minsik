// 즉시 실행 함수

function replyList() {
// board_id를 가지고 board_tb1의 덧글을 가져와야
// select * from reply_tb1 where board_id=2;
// replyRepository.findAllByBoardEntity 와 같다 .

const boardId=$('input#id').val(); // id는 # 으로 가져옴
// alert(boardId);

$.ajax({
 type: 'get',
 url : "/reply.replyList" + boardId,
 success:function(res){
 alert(`응답 : ${res}`) ;
 },
 error:function() {


  }
 });
}

// id 가 replyBtn  replyBtn 버튼
const replyBtn = $('#replyBtn');

// replyBtn 버튼을 클릭하면 ajaxWrite 함수를 실행해라
replyBtn.on('click' , ajaxWrite);
function ajaxWrite(){

  const boardId = $('#boardId').val();
  const replyContent=$('#replyContent').val();
  const replyWriter=$('#replyWriter').val();

  console.log(`ajaxWrite 함수 호출`);

  const dataVal = {

  // 왼쪽은 dto 와 일치 해야함

  'boardId' : boardId,
  'replyContent' : replyContent,
  'replyWriter' : replyWriter



  };

  console.log(dataVal);


$.ajax({
  type : 'post',
  url : '/reply/ajaxWrite',
  data : dataVal, // data type form

  success:function(res) {
  alert(`덧글 등록 하였습니다 .`);
     console.log(`게시글 번호: ${res.boardEntity.id}`)
        console.log(`덧글 번호: ${res.id}`)
        console.log(`덧글 내용: ${res.replyContent}`)
        console.log(`덧글 작성자: ${res.replyWriter}`)
        console.log(`덧글 작성시간: ${res.createTime}`)
        console.log(`덧글 수정시간: ${res.updateTime}`)

        let htmlData=`<tr>
              <td>${res.boardEntity.id}</td>
              <td>${res.id}</td>
              <td>${res.replyContent}</td>
              <td>${res.replyWriter}</td>
              <td>${res.createTime}</td>
        </tr>`;

        $(".tData").append(htmlData);


  },
  error:function() {
  alert("fail!")
  }
});

}


