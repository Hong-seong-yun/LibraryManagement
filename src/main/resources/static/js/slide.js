const slide = document.querySelector('.slides'); //전체 슬라이드 컨테이너
let slideItems = document.querySelectorAll('.slides li'); //모든 슬라이드들
let currSlide = 0; //현재 슬라이드 index
const maxSlide = slideItems.length; // 슬라이드 개수
const prev = document.querySelector('.prev'); //이전 버튼
const next = document.querySelector('.next'); //다음 버튼
const slideWidth = 170; //한개의 슬라이드 넓이
const slideMargin = 30; //슬라이드간의 margin 값

makeClone();
initfunction();

function makeClone() {
    // 뒤에 붙여주기
    for (var i = 0; i<maxSlide; i++) {
        slide.append(slideItems[i].cloneNode(true));
    }
    // 앞에 붙여주기
    for (var i = maxSlide-1; i>=0; i--) {
        slide.insertBefore(slideItems[i].cloneNode(true), slide.firstElementChild);
    }
}

// 슬라이드 넓이와 위치값 초기화 함수
function initfunction() {
    setTimeout(function () {
        slide.style.width = (slideWidth + slideMargin) * (maxSlide + 12) + 'px';
        slide.style.left = -(slideWidth + slideMargin) + 'px';
    }, 1000);
}

function moveSlide(num) {
    slide.style.left = -num * 400 + 'px';
    currentIdx = num;
}
function prevMove() {
    //이전 버튼 눌렀을때
    if (currSlide >= 0) {
        slide.style.left = -currSlide * (slideWidth + slideMargin) + 'px';
        slide.style.transition = `${0.5}s ease-out`;
    }
    if (currSlide === 0) {
        setTimeout(function () {
            slide.style.left = -maxSlide * (slideWidth + slideMargin) + 'px';
            slide.style.transition = `${0}s ease-out`;
        }, 500);
        currSlide = maxSlide;
    }
    currSlide -= 1;
}
function nextMove() {
    if(currSlide <= maxSlide - 1) {
        //슬라이드이동
        slide.style.left = -(currSlide + 2) * (slideWidth + slideMargin) + 'px';
        slide.style.transition = `${0.5}s ease-out`; //이동 속도
    }
    if (currSlide === maxSlide - 1) {
        //마지막 슬라이드 일때
        setTimeout(function () {
        //0.5초동안 복사한 첫번째 이미지에서, 진짜 첫번째 위치로 이동
        slide.style.left = -(slideWidth + slideMargin) + 'px';
        slide.style.transition = `${0}s ease-out`;
      }, 500);
      currSlide = -1;
    }
    currSlide += 1;
}

prev.addEventListener('click', () => {
    clearInterval(loopInterval);
    nextMove();
});

next.addEventListener('click', () => {
    clearInterval(loopInterval);
    prevMove();
});

// 기본적으로 슬라이드 루프 시작하기
let loopInterval = setInterval(() => {
    nextMove();
}, 2000);

// 슬라이드에 마우스가 올라간 경우 루프 멈추기
slide.addEventListener("mouseover", () => {
    clearInterval(loopInterval);
});

// 슬라이드에서 마우스가 나온 경우 루프 재시작하기
slide.addEventListener("mouseout", () => {
    loopInterval = setInterval(() => {
        nextMove();
    }, 2000);
});