let show = 0;

function showAdminTools(){
    if(show == 0){
        let delUser = document.getElementById("delTool");
            delUser.style.display = "block";
            delUser.style.position = "relative";
            let posicion = -300;
        let intervalo = setInterval(frame, 5);
        function frame() {
            if (posicion == 0) {
                clearInterval(intervalo);
            } else {
                posicion++;
                delUser.style.left = posicion + 'px';
            }
        }
        show = 1;
    }else{
        let delUser = document.getElementById("delTool");
            delUser.style.position = "relative";
            let posicion = 0;
        let intervalo = setInterval(frame, 5);
        function frame() {
            if (posicion == -400) {
                clearInterval(intervalo);
            } else {
                posicion--;
                delUser.style.left = posicion + 'px';
            }
        }
        show = 0;
        
    }
}
