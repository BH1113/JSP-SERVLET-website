let jobList = {  '1' : '학생', '2' : '프로그래머' , '3' : '서버엔지니어', '4' : '디자이너' };

let optionEl = document.createElement("option");

let addJobMenu = () => {
    let $job = document.querySelector(".jobSelect");
    let jobSelected = $job.dataset.id;
    let menu = document.createDocumentFragment();
    let optionEl = document.createElement("option");
    optionEl.value = jobSelected;
    optionEl.textContent = jobList[jobSelected];

    delete jobList[jobSelected - 1];

    for (i in jobList) {
        let optionEl2 = document.createElement("option");
        optionEl2.value = i;
        optionEl2.textContent = jobList[i];
        menu.appendChild(optionEl2);
    }
    $job.appendChild(menu);
};


