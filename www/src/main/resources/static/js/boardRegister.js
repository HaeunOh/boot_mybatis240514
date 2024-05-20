//트리거 버튼처리
document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('files').click();
});

//실행파일에 대한 정규표현식 작성 //파일 최대 사이즈
const regExp = new RegExp("\.(exe|sh|bat|dll|jar|msi)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener('change',(e)=>{
    if(e.target.id === 'files'){
        //files input type="file" element에 저장된 file의 정보를 가져오는 property
        const fileObj = document.getElementById('files').files;
        document.getElementById('regBtn').disabled = false;
        let div = document.getElementById('fileZone');
        div.innerHTML = '';
        let isOk = 1;
        let ul = `<ul class="list-group list-group-flush">`;
        for(let file of fileObj){
            let validResult = fileValidation(file.name, file.size);
            isOk *= validResult;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${validResult ? '<div class="fw-bold">업로드 가능</div>' : '<div class="fw-bold text-danger">업로드 불가능</div>'}`;
            ul += `${file.name}</div>`
            ul += `<span class="badge text-bg-${validResult ? 'success' : 'danger'}">${byteConvertor(file.size)}${file.size > 1024*1024 ? "MB" : (file.size > 1024 ? "KB" : "Bytes")}</span>`
            ul += `</li>`
        }
        ul += `</ul>`
        div.innerHTML = ul;
        if(isOk === 0){
            document.getElementById('regBtn').disabled = true;
        }
    }
})
function byteConvertor(bytes) {
    let e = Math.floor(Math.log(bytes)/Math.log(1024));
    return (bytes/Math.pow(1024, Math.floor(e))).toFixed(2);
}
