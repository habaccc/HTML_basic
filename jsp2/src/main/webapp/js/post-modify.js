/**
 * post-modify.js
 * /post/modify.jsp에서 사용됨.
 */

// jsp는 서버가 실행하고, js는 브라우저가 실행 (중요)
 document.addEventListener('DOMContentLoaded', function () {
     // form 요소를 찾음.
     const form = document.querySelector('#postModifyForm');
     
     // input#id 요소를 찾음.
     const inputId = document.querySelector('input#id');
     
     // input#title 요소를 찾음.
     const inputTitle = document.querySelector('input#title'); // 대소문자 구별하니까 주의
     
     // textarea#content 요소를 찾음.
     const textareaContent = document.querySelector('textarea#content');
     
     // 수정 완료 버튼을 찾음.
     const btnUpdate = document.querySelector('button#btnUpdate');
     
     // 삭제 버튼을 찾음.
     const btnDelete = document.querySelector('button#btnDelete');
     
     // 삭제 버튼에 클릭 이벤트 리스너(핸들러)를 등록.
     btnDelete.addEventListener('click', (e) => { // 익명함수 대신에 화살표 함수를 사용해도 됨.
         e.preventDefault();
         // -> form 안에 있는 버튼 또는 input[type=submit]의 기본 (클릭) 동작은
         // 폼의 내용을 서버로 제출(서버로 새로운 요청을 보냄).
         // 버튼의 기본 동작이 기능하지 않도록 함.
         
//       alert('삭제 버튼 클릭됨.');
         const id = inputId.value; // 아이디 값을 읽어들임
         const result = confirm(`NO. ${id}을 정말 삭제할까요?`); // js에서 `을 하면 문자열 템플릿이다 `안쪽에서 ${} 하면 
         // 확인 -> true, 취소 -> false 리턴.
         console.log(`삭제 확인 결과 = ${result}`); // 브라우저 개발자도구의 콘솔창 로그.
        
         // 사용자가 confirm 창에서 '확인'을 클릭했을 때
         if (result) {
             form.action = 'delete'; // 폼 제출(요청) 주소 // 삭제했으면 요청주소를 불러줌
             form.method = 'post'; // 요청 방식 post 방식 설정
             form.submit(); // 폼 제출(요청 보내기)
         }
     });
     
     btnUpdate.addEventListener('click', (e) => {
         e.preventDefault(); // 기본 동작인 폼 제출 기능을 막음.
         
         const id = inputId.value; // 포스트 번호
         const title = inputTitle.value; // 포스트 제목
         const content = textareaContent.value; // 포스트 내용
         
         if (title === '' || content === '') {
             // 제목 또는 내용이 비어 있으면 
             alert('제목과 내용은 반드시 입력해야 합니다.');
             return; // 함수 종료
         }
         
         const result = confirm(`NO.${id} 포스트를 업데이트할까요?`);
         if (result) {
             form.action = 'update'; // 업데이트 요청 주소
             form.method = 'post'; // 요청 방식 
             form.submit(); // 폼 제출(요청 보내기)
         }
         
     });
     
 });
 