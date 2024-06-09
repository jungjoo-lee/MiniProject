function viewGoodsDetail(gseq) {
	location.href = "gshop.do?command=goodsDetailView&gseq=" + gseq;
}


function check_all() {
	let checkAll = document.querySelector("#checkAll");

	if (checkAll != null) {
		checkAll.addEventListener("click", () => {
			let checkboxes = document.querySelectorAll("#checkboxes");
			if (checkboxes.length > 0) {
				Array.from(checkboxes).forEach((checkbox) => {
					checkbox.checked = checkAll.checked;
				});
			} else {
				console.log("No checkboxes found with the name 'gseq'");
			}
		})
	}
}

check_all();

function toggle_best() {
	let best = document.querySelector("#bestToggle");

	if (best != null) {
		best.addEventListener("click", () => {

			let count = 0;
			let gseq = document.goodsViewForm.gseq;

			if (gseq.length == undefined) {
				if (gseq.checked == true) {
					count++;
				}
			} else {
				for (let i = 0; i < gseq.length; i++) {
					if (gseq[i].checked == true) {
						count++;
					}
				}
			}

			if (count == 0) {
				alert("상태를 변경할 상품을 선택해주세요");
			} else {
				document.goodsViewForm.method = "post";
				document.goodsViewForm.action = "gshop.do?command=adminBestToggle"
				document.goodsViewForm.submit();
			}
		})
	}
}
toggle_best();

function toggle_use() {
	let useyn = document.querySelector("#useynToggle");

	if (useyn != null) {
		useyn.addEventListener("click", () => {

			let count = 0;
			let gseq = document.goodsViewForm.gseq;

			if (gseq.length == undefined) {
				if (gseq.checked == true) {
					count++;
				}
			} else {
				for (let i = 0; i < gseq.length; i++) {
					if (gseq[i].checked == true) {
						count++;
					}
				}
			}

			if (count == 0) {
				alert("상태를 변경할 상품을 선택해주세요");
			} else {
				document.goodsViewForm.method = "post";
				document.goodsViewForm.action = "gshop.do?command=adminUseYnToggle"
				document.goodsViewForm.submit();
			}
		})
	}
}
toggle_use();

function delete_goods() {
	let del = document.querySelector("#deleteGoods");

	if (del != null) {
		del.addEventListener("click", () => {

			let count = 0;
			let gseq = document.goodsViewForm.gseq;

			if (gseq.length == undefined) {
				if (gseq.checked == true) {
					count++;
				}
			} else {
				for (let i = 0; i < gseq.length; i++) {
					if (gseq[i].checked == true) {
						count++;
					}
				}
			}

			if (count == 0) {
				alert("삭제할 상품을 선택해주세요");
			} else {
				document.goodsViewForm.action = "gshop.do?command=adminGoodsDelete"
				document.goodsViewForm.method = "post";
				document.goodsViewForm.submit();
			}
		})
	}
}
delete_goods();

function category_view() {
	let cate = document.querySelector("#selectCategory");

	if (cate != null) {
		cate.addEventListener("click", () => {
			if (cate.value != "0") {
				document.goodsViewForm.action = "gshop.do?command=adminCategoryView"
				document.goodsViewForm.method = "post";
				document.goodsViewForm.submit();
			}
		})
	}
}

category_view();

function go_Search() {
	let goSearch = document.querySelector("#goSearch");

	if (goSearch != null) {
		goSearch.addEventListener("click", () => {
			document.goodsViewForm.action = "gshop.do?command=adminGoodsSearch"
			document.goodsViewForm.method = "post";
			document.goodsViewForm.submit();
		})

	}

}

go_Search();