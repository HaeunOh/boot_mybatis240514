document.getElementById('modBtn').addEventListener('click',()=>{
    document.getElementById('n').readOnly = false;
    let modBtn = document.createElement('button');
    modBtn.setAttribute('type','submit');
    modBtn.classList.add('btn','btn-outline-primary');
    modBtn.innerText="Submit";
    document.getElementById('modForm').appendChild(modBtn);
    document.getElementById('modBtn').remove();
    document.getElementById('delBtn').remove();
    document.getElementById('passDiv').style.display='';
})