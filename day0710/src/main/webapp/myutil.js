/**
 * 
 */
// 객체의 배열을 매개변수로 전달받아 테이블을 생성하여 반환하는 함수
let makeTableFromObjectArr = function(arr){
	let str ="";
	str += "<table border ='1' width ='80%'>";
	str += "<tr>";
	for(let key in arr[0]){
		str += "<th>"+key+"</th>"
	}
	str += "</tr>";
	
	for(i =0; i<arr.length; i++){
		let row = arr[i];
		str += "<tr>";
		for(let key in row){
			str += "<td>"+row[key]+"</td>";
		}
		str += "</tr>";
	}
	str += "</table>";
	return str;
}

 let makeListFromArr = function(data){
	let str = "";
	str += "<ul>";
	for(i=0; i<data.length; i++){
		str += "<li>"+data[i]+"</li>";
	}
	str += "</ul>";
	return str;
 }