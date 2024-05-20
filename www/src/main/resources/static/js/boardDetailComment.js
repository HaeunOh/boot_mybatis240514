document.getElementById('cmtAddBtn').addEventListener('click', () => {
    let cmtWriter = document.getElementById('cmtWriter').value;
    let cmtText = document.getElementById('cmtText').value;

    if(cmtText == null || cmtText === ''){
        alert("댓글을 입력해주세요. ")
        document.getElementById('cmtText').focus();
        return false;
    }else{
        let cmtData = {
            bno : bnoVal,
            writer : cmtWriter,
            content : cmtText
        }
        postCommentToServer(cmtData).then(result => {
            if(result === "1"){
                alert("댓글 입력 성공");
                document.getElementById('cmtText').value = "";
                spreadList(bnoVal);
            }
        })
    }
});

async function postCommentToServer(cmtData){
    try {
        const url = '/comment/post';
        const config = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
function spreadList(bno, page=1){
    getCommentListFromServer(bno, page).then(result =>{
        const ul = document.getElementById('cmtListArea');
        if(result.cmtList.length > 0){
            if(page===1){
                ul.innerHTML = "";
            }
            for(let cvo of result.cmtList){
                let add = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                add+= `<div class="mb-3"> no. ${cvo.cno}`;
                add+= `<div class="fw-bold">${cvo.writer}</div>`;
                add+= `${cvo.content}`;
                add+= `</div>`;
                add+= `<span class="badge rounded-pill text-bg-warning">${cvo.regDate}</span><br>`;
                //수정, 삭제 버튼
                if(cvo.writer === authNickname || admin === true){
                add += `<button type="button" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                add += `<button type="button" class="btn btn-outline-danger btn-sm del">삭제</button>`;
                }
                add+= `</li>`;
                ul.innerHTML += add;
            }
            let moreBtn = document.getElementById('moreBtn');
            if(result.pgvo.pageNo < result.realEndPage){
                moreBtn.style.visibility = "visible";
                moreBtn.dataset.page = page + 1;
            }else{
                moreBtn.style.visibility = "hidden";
            }

        }else{
            ul.innerHTML = `<li class="list-group-item"> Comment List Empty </li>`
        }
    })
}

async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click', (e)=>{
    if(e.target.id === 'moreBtn'){
        let page = parseInt(e.target.dataset.page);
        spreadList(bnoVal, page);
    }else if(e.target.classList.contains('mod')){
        let li = e.target.closest('li');
        //nextSibling : 한 부모 안에서 다음형제를 찾기
        let cmtText = li.querySelector('.fw-bold').nextSibling;
        document.getElementById('modWriter').innerText = li.querySelector('.fw-bold').innerText;
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;
        document.getElementById('cmtModBtn').setAttribute("data-cno", li.dataset.cno);
    }else if(e.target.id === 'cmtModBtn'){
        let cmtModData = {
            cno : e.target.dataset.cno,
            content : document.getElementById('cmtTextMod').value
        };
        commentModDataToServer(cmtModData).then(result =>{
            if(result === "1"){
                alert("댓글 수정 성공");
                spreadList(bnoVal);
            }
        })
    }else if(e.target.classList.contains('del')){
        let li = e.target.closest('li');
        let cno = li.dataset.cno;
        commentDeleteFromServer(cno).then(result =>{
            if(result === "1"){
                alert("댓글 삭제 성공");
                spreadList(bnoVal);
            }
        })
    }
})

async function commentModDataToServer(cmtModData){
    try {
        const url = '/comment/update';
        const config = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtModData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
async function commentDeleteFromServer(cno){
    try {
        const url = '/comment/'+cno;
        const config = {
            method: 'DELETE'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}