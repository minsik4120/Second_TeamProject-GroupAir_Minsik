
// JavaScript 코드
const memberIdInput = document.querySelector('#id');
const userPwInput = document.querySelector('#userPw');
const userEmailInput = document.querySelector('#userEmail');
const roleInput = document.querySelector('#role');
const addressInput = document.querySelector('#address');
const phoneInput = document.querySelector('#phone');
const employeeDateInput = document.querySelector('#employeeDate');
const resignationDateInput = document.querySelector('#resignationDate');
const memberDetail = document.querySelector('#memberDetail');

function memberDetailBtnFn(event, id) {
  event.preventDefault();
//  console.log("Member ID:", id);
  const url = "/member/memberDetail/" + id;
  fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      memberIdInput.value = data.id;
      userPwInput.value = data.userPw;
      userEmailInput.value = data.userEmail;
      roleInput.value = data.role;
      addressInput.value = data.address;
      phoneInput.value = data.phone;
      employeeDateInput.value = data.employeeDate;
      resignationDateInput.value = data.resignationDate;

      memberDetail.style.display = 'block';
    }).catch((error) => {
      console.log(error);
    });
}

// 수정

const updateBtn = document.querySelector('#updateBtn');
updateBtn.addEventListener('click', (event) => {

event.preventDefault();

  const member_data = {
    'id':id1.value,
    'pw': pw1.value,
    'email': email1.value,
    'nickName': nickName1.value,
    'createTime': createTime1.value,
    'updateTime':updateTime1.value,
  };
  const url = "/api/admin/member/update";
  fetch(url, {
      method: "PUT",
      body: JSON.stringify(member_data),
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      id1.value = data.id;
      pw1.value = data.pw;
      nickName1.value = data.nickName;
      email1.value = data.email;
      createTime1.value = data.createTime;
      updateTime1.value = data.updateTime;

      ajaxMemberList();
    }).catch((error) => {
      console.log(error);
    });
});



// 삭제
const deleteBtn = document.querySelector('#deleteBtn');

deleteBtn.addEventListener('click', (event) => {

event.preventDefault();

 const id1 = document.querySelector('#id1');

  const url = "/api/admin/member/delete/"+id1.value;
  fetch(url, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => response.json())
    .then((data) => {
      if(data!=1){
          alert("삭제 Fail!!")
      }else{
          alert("삭제 Success!!");
          memberDetail.style.display='none';
          ajaxMemberList();
      }
    }).catch((error) => {
      console.log(error);
    });
});
