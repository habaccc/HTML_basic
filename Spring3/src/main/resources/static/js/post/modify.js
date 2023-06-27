/**
 * 포스트 업데이트 & 삭제
 */

document.addEventListener('DOMContentLoaded', () => {

    const modifyForm = document.querySelector('#postModifyForm');
    const id = document.querySelector('input#id').value;

    const btnUpdate = document.querySelector('#btnUpdate');
    btnUpdate.addEventListener('click', (e) => {
        const title = document.querySelector('input#title').value;
        const content = document.querySelector('textarea#content').value;
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return;
        }

        const check = confirm(`NO.${id} 포스트의 변경 내용을 저장할까요?`);
        if (check) {
            modifyForm.action = './update'; // 폼 요청 주소
            modifyForm.method = 'post'; // 폼 요청 방식
            modifyForm.submit();
        }
    });

    const btnDelete = document.querySelector('#btnDelete');
    btnDelete.addEventListener('click', (e) => {
        const check = confirm('정말 삭제할까요?');
        if (check) {
            modifyForm.action = './delete?id=' + id.value;            
            modifyForm.method = 'post';
            modifyForm.submit();
        }
    });
});