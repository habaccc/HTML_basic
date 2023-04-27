const about = document.querySelector(".about");
// about 클래스를 가진 요소를 선택하고 -> about 변수에 할당
const sections = about.querySelectorAll("div");
// about 요소 내의 모든 div 요소를 선택하고 -> sections 변수에 할당.
const quickIconContainer = document.querySelector(".quick-icons");
// quick-icons 클래스를 가진 요소를 선택하고 -> quickIconContainer 변수에 할당.
const icons = quickIconContainer.querySelectorAll("li");
// quickIconContainer 요소 내의 모든 li 요소를 선택 -> icons 변수에 할당

const homeIcon = icons[0].firstChild;
homeIcon.addEventListener("click", () => {
  icons.forEach((icon) => icon.classList.remove("icon-active"));
});
// icons 배열에서 첫 번째 li요소의 첫 번째 자식 요소를 선택 -> homeicon 변수에 할당.
// homeicon 요소에 클릭 이벤트 리스너 추가 -> 이벤트가 발생하면 콜백 함수 실행.
// 콜백 함수는 icons 배열의 모든 요소를 반복하면서 icon-active 클래스를 제거.
// 페이지의 홈 아이콘이 클릭되면 다른 아이콘에 적용되어 있는 icon-active를 모두 제거하는 역할.

const options = {
  root: null, //default : viewport .. 부모컨테이너를 지정해줄 수 있다.
  rootMargin: `0px`, //마진을 주면, 내가 보는 화면보다 기준영역이 그 만큼 더 늘어나게된다.
  threshold: 0.5, //얼마만큼 보여져야 콜백함수를 호출할지 결정  0~1 줄 수 있다.
};

const callback = (entries, observer) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      switch (entry.target.id) {
        case "about__age":
          icons.forEach((icon) => icon.classList.remove("icon-active"));
          icons[1].classList.add("icon-active");
          break;
        case "about__traveling":
          icons.forEach((icon) => icon.classList.remove("icon-active"));
          icons[2].classList.add("icon-active");
          break;

        case "about__concert":
          icons.forEach((icon) => icon.classList.remove("icon-active"));
          icons[3].classList.add("icon-active");
          break;
        case "about__major":
          icons.forEach((icon) => icon.classList.remove("icon-active"));
          icons[4].classList.add("icon-active");
          break;
        case "about__graduate":
          icons.forEach((icon) => icon.classList.remove("icon-active"));
          icons[5].classList.add("icon-active");
          break;
        case "about__wecode":
          icons.forEach((icon) => icon.classList.remove("icon-active"));
          icons[6].classList.add("icon-active");
          break;
        default:
          icons.forEach((icon) => icon.classList.remove("icon-active"));
          break;
      }
    } else {
      //   console.error(entry.target);
      switch (entry.target.id) {
        case "about__age":
          icons.forEach((icon) => icon.classList.remove("icon-active"));
          break;
      }
    }
  });
};

const observer = new IntersectionObserver(callback, options);
sections.forEach((section) => observer.observe(section)); //section들을 관찰자가 관찰하도록 명령

// aside On and Off
// at footer
const footerOptions = {
  threshold: 0.5,
};

const handleFooter = (entries) => {
  if (entries[0].isIntersecting) {
    quickIconContainer.classList.add("show-off");
  } else {
    quickIconContainer.classList.remove("show-off");
  }
};

const footerObserver = new IntersectionObserver(handleFooter, footerOptions);
const footer = document.querySelector("footer");
footerObserver.observe(footer);

//at header
const home = document.querySelector(".home");
const homeOptions = {
  threshold: 0.8,
};

const handleHome = (entries) => {
  if (entries[0].isIntersecting) {
    quickIconContainer.classList.add("show-off");
  } else {
    quickIconContainer.classList.remove("show-off");
  }
};

const headerObserver = new IntersectionObserver(handleHome, homeOptions);
headerObserver.observe(home);
