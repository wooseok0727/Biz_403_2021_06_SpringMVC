document.addEventListener("DOMContentLoaded",()=>{
	
	document.querySelector("table.container").addEventListener("click",(e)=>{
		
		let tagName = e.target.tagName;
		if(tagName == "TD") {
			let urlPath = `${rootPath}`;
			let tr = e.target.closest("TR").dataset;
			if(tr.insert == "1") {
				return false;
			} else {
				let st_num = tr.num;
				location.href = urlPath + "/editlist?st_num=" + st_num;
			}				
		}
	});
});