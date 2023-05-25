/**
 * reply.js
 * 댓글 등록, 목록 검색, 수정, 삭제
 * /post/detail.jsp에 포함.
 */

document.addEventListener('DOMContentLoaded', () => {
    // 댓글 개수 표시 영역
    const replyCountSpan = document.querySelector('span#replyCount');

    // 댓글 목록 표시 영역(div)
    const replies = document.querySelector('div#replies');

    // 댓글 삭제 버튼의 이벤트리스너 함수(콜백함수)
    const deleteReply = (e) => {
        //console.log(e);
        console.log(e.target); // e.target: 이벤트가 발생한 타겟. 여기서는 삭제 버튼.
        
        if (!confirm('정말 삭제할까요?')) {
            return;
        }
        
        // 삭제할 댓글 아이디:
        const id = e.target.getAttribute('data-id');
        
        // 삭제 요청 URL
        const reqUrl = `/spring2/api/reply/${id}`;
        
        // 삭제 요청을 Ajax 방식으로 보냄.
        axios.delete(reqUrl)
            .then((response) => {
                console.log(response);
                alert('댓글 삭제 성공');
                getRepliesWithPostId(); // 댓글 목록 갱신.
            }) // 성공
            .catch((error) => {
                console.log(error)
            }); // 실패
    };
    
    // 댓글 수정 모달 객체를 생성
    const modal = new bootstrap.Modal('div#replyUpdateModal', {backdrop: false});
    
    // 모달의 엘리먼트 찾기
    const modalInput = document.querySelector('input#modalReplyId');
    const modalTextarea = document.querySelector('textarea#modalReplyText');
    const modalBtnUpdate = document.querySelector('button#modalBtnUpdate');
    
    
    // 댓글 수정 버튼의 이벤트리스너 함수(콜백함수) - 댓글 수정 모달을 보여주는 함수
    const showUpdateModal = (e) => {
        //console.log(e);
        //console.log(e.target);
        const id = e.target.getAttribute('data-id');
        const reqUrl = `/spring2/api/reply/${id}`;
        axios.get(reqUrl) // 서버로 GET 방식의 Ajax 요청을 보냄
            .then((response) => {
                // reponse에 포함된 data 객체에서 id, replyText 값을 찾음.
                const {id, replyText} = response.data;
                
                // id와 replyText를 모달의 input과 textarea에 씀.
                modalInput.value = id;
                modalTextarea.value = replyText;
                
                // 모달을 보여줌.
                modal.show();
                
            }) // 성공 응답이 왔을 때 실행할 콜백을 등록
            .catch((error) => console.log(error)); // 실패 응답이 왓을 때 실행할 콜백 등록.
    };
    
    const updateReply = () => {
        // 수정할 댓글 아이디
        const id = modalInput.value;
        // 수정할 댓글 내용
        const replyText = modalTextarea.value;
        // PUT 방식의 Ajax 요청을 보냄.
        const reqUrl = `/spring2/api/reply/${id}`;
        const data = { replyText }; // 원래 자바스크립트의 객체를 선언할때에는, { key: value }이런식으로 써주어야 함. { replyText: replyText}
        // 그런데 키 값이 그 키에들어가는 밸류의 변수이름과 동일한 경우에는 { replyText } 라고 써도 됨.
        
        // Ajax요청에 대한 성공/실패 콜백을 등록.
        axios.put(reqUrl,data)
            .then((response) => { // 수정을 성공 했을 때
                alert(`댓글 업데이트 성공(${response.data})`)
                getRepliesWithPostId(); // 댓글 목록 업데이트
            })
            .catch((error) => console.log(error)) // 실패했을때 로그 출력.
            .finally(() => modal.hide()); // 성공했든 실패했든 모달을 닫기.
    };
    
    // 모달에서 [수정 내용 저장] 버튼 이벤트 리스너 등록.
    modalBtnUpdate.addEventListener('click', updateReply); // 함수는 간단하면 화살표함수로 만들어도 되지만 내용이 길어진다면 함수의 이름을 미리
    
    
    
    // 댓글 목록 HTML을 작성하고 replies 영역에 html을 추가하는 함수.
    // argument data: Ajax 요청의 응답으로 전달받은 데이터.
    const makeReplyElements = (data) => {
        // 댓글 개수 업데이트
        replyCountSpan.innerHTML = data.length; // 배열 길이(원소 개수)

        replies.innerHTML = ''; // <div>의 컨텐트를 지움.

        let htmlStr = ''; // let : 변경할 수 있음. 문자열을 계속해서 변경할 것이기 때문에.
        // for (let i = 0; i < data.length; i++) {}
        // for (let x in data) {} -> 인덱스 interation(반복)
        for (let reply of data) {
            console.log(reply);
            // Timestamp 타입 값을 날짜/시간 타입 문자열로 변환:
            const modified = new Date(reply.modifiedTime).toLocaleString();

            // 댓글 1개를 표시할 HTML 코드:
            htmlStr += `
             <div class="my-2 card">
                <div>
                    <span class="d-none">${reply.id}</span>
                    <span class="fw-bold">${reply.writer}</span>
                    <span class="text-secondary">${modified}</span>
                </div>
                <div>
                    ${reply.replyText}
                </div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <button class="btnDelete btn btn-outline-danger btn-sm" data-id="${reply.id}">
                    삭제
                    </button>
                    <button class="btnModify btn btn-outline-success btn-sm" data-id="${reply.id}">
                    수정
                    </button>
                </div>
             </div>
             `;
        }

        // 작성된 HTML 코드를 replies <div> 영역 안에 포함.
        replies.innerHTML = htmlStr;

        // 모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 등록:
        const deleteButtons = document.querySelectorAll('button.btnDelete');
        for (let btn of deleteButtons) {
            btn.addEventListener('click', deleteReply);
        }

        // 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 등록:
        const modifyButtons = document.querySelectorAll('button.btnModify');
        for (let btn of modifyButtons) {
            btn.addEventListener('click', showUpdateModal);
        }
    };


    const getRepliesWithPostId = async () => {
        // 댓글 목록을 요청하기 위한 포스트 번호(아이디)
        const postId = document.querySelector('input#id').value;

        // 댓글 목록을 요청할 URL
        const reqUrl = `/spring2/api/reply/all/${postId}`; // 자바스크립트 변수를 문자열 안에 넣고싶을때 ``을 사용.

        // Ajax 요청을 보내고 응답을 기다림.
        try {
            const response = await axios.get(reqUrl); // 요청을 보냄.
            console.log(response);
            // 댓글 개수 업데이트 & 댓글 목록 보여주기
            makeReplyElements(response.data); 

        } catch (error) {
            console.log(error);
        }
    };

    // 부트스트랩 collapse 객체를 생성 - 초기 상태는 화면에서 안보이는 상태
    const bsCollapse = new bootstrap.Collapse('div#replyToggleDiv', { toggle: false });

    // 버튼 아이콘 이미지
    const toggleBtnIcon = document.querySelector('img#toggleBtnIcon');

    // 댓글 등록/ 목록 보이기/숨기기 토글 버튼에 이벤트 리스너를 등록
    const btnToggleReply = document.querySelector('button#btnToggleReply');
    btnToggleReply.addEventListener('click', () => {
        bsCollapse.toggle();
        const toggle = btnToggleReply.getAttribute('data-toggle');
        if (toggleBtnIcon.alt === 'toggle-off') {
            toggleBtnIcon.src = '/spring2/static/assets/icons/toggle2-on.svg';
            toggleBtnIcon.alt = 'toggle-on';

            // 댓글 전체 목록을 서버에서 불러오고(서버에 요청), 응답이 오면 화면을 갱신
            getRepliesWithPostId();

        } else {
            toggleBtnIcon.src = '/spring2/static/assets/icons/toggle2-off.svg';
            toggleBtnIcon.alt = 'toggle-off';
            replies.innerHTML = '';
        }
    });

    const btnAddReply = document.querySelector('button#btnAddReply');
    const createReply = (e) => { // 콜백 함수
        // axios 라이브러리를 사용해서 Ajax 요청을 보냄.
        const postId = document.querySelector('input#id').value;
        const replyText = document.querySelector('textarea#replyText').value;
        const writer = document.querySelector('input#writer').value;

        if (replyText === '') {
            alert('댓글 내용을 입력하세요.');
            return;
        }

        const data = { postId, replyText, writer, };

        axios.post('/spring2/api/reply', data) // post 방식의 Ajax 요청 보냄.
            .then((response) => { // 성공했을 때
                alert(`댓글 등록 성공(${response.data})`);

                // 댓글 입력 창의 내용을 지움.
                document.querySelector('textarea#replyText').value = '';

                // 댓글 목록을 새로 고침.
                getRepliesWithPostId();

            }) // 성공 응답이 왔을 때 실행할 콜백 함수 등록
            .catch((error) => {
                console.log(error);
            }); // 실패 응답이 왔을 때 실행할 콜백 함수 등록

    };
    btnAddReply.addEventListener('click', createReply);

});