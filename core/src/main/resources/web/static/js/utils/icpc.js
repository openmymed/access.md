/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function icpc(){};
icpc.prototype.loadIcpc = () => {
  return  fetch("/api/symptom/codes", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  }).then((res) => {
    if (res.ok) {
      return res.json();
    } else {
      alert("Wrong username or password");
    }
  }).then(data => {
    window.localStorage.setItem("icpc", JSON.stringify(data))
  })
}


icpc.prototype.getTitle = (code) => {
  let i = 0;
  let codes = JSON.parse(window.localStorage.getItem("icpc"));
  for (i; i < codes.length; i++) {
    if (codes[i].code === code) {
      return codes[i].title;
    }
  }
  return "CODE " + code + " NOT FOUND";
}
window.icpcService = new icpc();