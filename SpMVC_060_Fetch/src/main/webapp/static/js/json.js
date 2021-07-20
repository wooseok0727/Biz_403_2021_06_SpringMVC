let button1 = document.querySelector("button#json_1");
if (button1) {
  button1.addEventListener("click", (e) => {
    const jsonData = {
      user_id: "hong@naver.com",
      name: "Hong",
      tel: "010-111-1111",
      age: 33,
    }; // jsonData

    fetch(`${rootPath}/json/dto`, {
      method: "POST",
      headers: {
        "content-Type": "application/json;char=UTF8",
      },
      // JSON 데이터를 문자열화(serialize)
      body: JSON.stringify(jsonData),
    })
      .then((res) => res.text())
      .then((result) => alert(result));
  }); // if문
}
