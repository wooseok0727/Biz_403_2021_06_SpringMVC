document.addEventListener("DOMContentLoaded", () => {
  let nav = document.querySelector("nav#main_nav");

  nav.addEventListener("click", (e) => {
    let tagName = e.target.tagName;
    if (tagName === "LI") {
      let menuText = e.target.textContent;

      let urlPath = `${rootPath}`;

      if (menuText === "HOME") {
        urlPath += "/";
      } else if (menuText === "학생정보") {
        urlPath += "/student";
      } else if (menuText === "성적일람표") {
        urlPath += "/score";
      } else if (menuText === "로그인") {
        urlPath += "/login";
      }
      location.href = urlPath;
    }
  });
});
