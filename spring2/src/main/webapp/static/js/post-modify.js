/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	 
	 const modifyForm = document.querySelector('#modifyForm');
	 
	 // 삭제 버튼을 찾아서 이벤트 리스너를 등록
     const btnDelete = document.querySelector('#btnDelete');
     btnDelete.addEventListener('click', () => {
		 const check = confirm('정말 삭제할까요?');
		 if (check) {
			 modifyForm.action = './delete'; // 'delete' // 폼 요청 주소
			 modifyForm.method = 'post'; // 폼 요청 방식
			 modifyForm.submit(); // 폼 제출 -> 요청을 서버로 보냄.
		 }
	 }
 
    btnUpdate.addEventListener('click', (e) => {
        e.preventDefault();
        
        const id = inputId.value;
        const title = inputTitle.value;
        const content = textareaContent.value;
        
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return;
        }
        
        const result = confirm(`NO.${id} 포스트를 업데이트할까요?`);
        if (result) {
            form.action = 'update';
            form.method = 'post';
            form.submit();
        }
        
    });
    
    btnDelete.addEventListener('click', (e) => {
        e.preventDefault();
        
        const id = inputId.value;
        const result = confirm(`NO. ${id}을 정말 삭제할까요?`);
        
        if (result) {
            form.action = 'delete';
            form.method = 'post';
            form.submit();
        }
    });
    
 
 });