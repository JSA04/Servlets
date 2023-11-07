const imgInput = document.getElementById('escolher_img');
const img = document.getElementById("img");

imgInput.onchange = function (e){

    var file = e.target.files[0];

    var reader = new FileReader();
    reader.onloadend = function(){
        img.style.backgroundImage = "url(" + reader.result + ")";
        img.style.backgroundSize = "contain";
        img.style.backgroundPosition = "center center";
        img.style.fontSize = "0";
    }
    if(file){
        reader.readAsDataURL(file);
    }
}