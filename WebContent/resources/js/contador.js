 var seg = 59;
    function countDown(tempo) {
        var btn = document.getElementById('btn');
        // Se o tempo não for zerado
        if ((tempo - 1) >= -1) {
            // Pega a parte inteira dos minutos
            var min = parseInt(tempo / 60);
            // Calcula os segundos restantes
            var seg = tempo % 60;
            // Formata o número menor que dez, ex: 08, 07, ...
            if (min < 10) {
                min = "0" + min;
                min = min.substr(0, 2);
            }
            if (seg <= 9) {
                seg = "0" + seg;
            }
            // Cria a variável para formatar no estilo hora/cronômetro
            horaImprimivel = min + ':' + seg;
            //JQuery pra setar o valor
            btn.value = horaImprimivel;
    // diminui o tempo
            tempo--;
            // Define que a função será executada novamente em 1000ms = 1 segundo
            setTimeout('countDown(' + tempo + ')', 1000);
            // Quando o contador chegar a zero faz esta ação

        } 
    }