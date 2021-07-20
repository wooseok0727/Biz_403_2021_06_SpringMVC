let button_1 = document.querySelector("button#form_1");
if (button_1) {
  button_1.addEventListener("click", (e) => {
    let form_1 = document.querySelector("form#user_form");
    if (form_1) {
      // form_1.submit();

      // JS 를 사용하여 서버로 form에 담긴 데이터 전달하기
      // 1. form에 담긴 데이터를 FormData 객체로 변환하기
      const formData = new FormData(form_1);
      const plainFormData = Object.fromEntries(formData.entries());
      const jsonFormData = JSON.stringify(plainFormData);

      const jsonOption = {
        method: "POST",
        headers: {
          "content-Type": "application/json;char=UTF8",
        },
        body: jsonFormData,
      };

      fetch(`${rootPath}/form`, jsonOption)
        .then((res) => res.json())
        .then((result) => {
          document.writeln(JSON.stringify(result));
        });
    }
  });
}
// URLSearchParams 으로 보낼시 @RequestBody 어노테이션 삭제
let button_2 = document.querySelector("button#form_2");
if (button_2) {
  button_2.addEventListener("click", (e) => {
    let form_1 = document.querySelector("form#user_form");
    if (form_1) {
      const formData = new FormData(form_1);
      fetch(`${rootPath}/form`, {
        method: "POST",
        body: new URLSearchParams(formData),
      })
        .then((res) => res.json())
        .then((result) => document.writeln(JSON.stringify(result)));
    }
  });
}
