document.getElementById('listBtn').addEventListener('click',()=>{
    location.href="/board/list";
})
document.body.addEventListener('click',(e)=>{
    if (e.target.id === 'delBtn'){
        document.getElementById('delForm').submit();
    }
    if(e.target.id === 'modBtn'){
        document.getElementById('t').readOnly = false;
        document.getElementById('c').readOnly = false;
        // <button></button>
        let modBtn = document.createElement('button');
        // type="submit"
        modBtn.setAttribute('type','submit');
        modBtn.classList.add('btn','btn-outline-warning');
        modBtn.innerText="Submit";
        // 생성한 버튼 modForm에 추가
        document.getElementById('modForm').appendChild(modBtn);
        //modBtn, delBtn 임시삭제
        document.getElementById('modBtn').remove();
        document.getElementById('delBtn').remove();
    }
})





