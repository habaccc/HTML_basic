/**
 * 
 */

 document.addEventListener('DOMContentLoaded', function () {
     
     const form = document.querySelector('#postModifyForm');
     
     const inputId = document.querySelector('input#id');
     
     const inputTitle = document.querySelector('input#title');
     
     const textareaContent = document.querySelector('textarea#content');
     
     const btnUpdate = document.querySelector('button#btnUpdate');

     const btnDelete = document.querySelector('button#btnDelete');
 
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