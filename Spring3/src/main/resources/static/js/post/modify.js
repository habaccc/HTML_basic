/**
 * 포스트 업데이트 & 삭제
 */

document.addEventListener('DOMContentLoaded', () => {

<<<<<<< HEAD
    const modifyForm = document.querySelector('#postModifyForm');
=======
	// form 요소를 찾음.
    const postModifyForm = document.querySelector('#postModifyForm');
>>>>>>> a8700e6 (...)
    const id = document.querySelector('input#id').value;

    const btnUpdate = document.querySelector('#btnUpdate');
    btnUpdate.addEventListener('click', (e) => {
<<<<<<< HEAD
        const title = document.querySelector('input#title').value;
        const content = document.querySelector('textarea#content').value;
=======
        const title = document.querySelector('#title').value;
        const content = document.querySelector('#content').value;
>>>>>>> a8700e6 (...)
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return;
        }

<<<<<<< HEAD
        const check = confirm(`NO.${id} 포스트의 변경 내용을 저장할까요?`);
        if (check) {
            modifyForm.action = './update'; // 폼 요청 주소
            modifyForm.method = 'post'; // 폼 요청 방식
            modifyForm.submit();
        }
=======
        const result = confirm(`포스트의 변경 내용을 저장할까요?`);
        if (!result) {
			return;
        }
		
		postModifyForm.action = '/post/update'; // 폼 요청 주소
        postModifyForm.method = 'post'; // 폼 요청 방식
        postModifyForm.submit();        
>>>>>>> a8700e6 (...)
    });

    const btnDelete = document.querySelector('#btnDelete');
    btnDelete.addEventListener('click', (e) => {
<<<<<<< HEAD
        const check = confirm('정말 삭제할까요?');
        if (check) {
            modifyForm.action = './delete?id=' + id.value;            
            modifyForm.method = 'post';
            modifyForm.submit();
        }
=======
        const result = confirm('정말 삭제할까요?');
        if (!result) {
           
        }
        
        postModifyForm.action = '/post/delete'; // submit 요청 주소            
        postModifyForm.method = 'post'; // submit 요청 방식
        postModifyForm.submit(); // 폼 제출(submit), 오청 보내기.
        
>>>>>>> a8700e6 (...)
    });
});