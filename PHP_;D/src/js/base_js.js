$(document).ready(function () {
    $("#playerCountDrop").change(function () {
        var playerCount = this.options[this.selectedIndex].text;
        var playerCountHtml="";
        for (var i = 1; i <= playerCount; i++) {
            var playerID = "#Player" + i+" input";
            if ($(playerID).length) {
                var PlayerName = $(playerID).val();
                playerCountHtml += "<div id='Player" + i + "'><label>Player: " + i + "</label><input name='Player"+i+"' type='text' value='"+PlayerName+"'></div>";
            }
            else {
                playerCountHtml += "<div id='Player" + i + "'><label>Player: " + i + "</label><input name='Player"+i+"' type='text'></div>";
            }
        }
        $("#players").html(playerCountHtml);
    });
    $("#drinkCountDrop").change(function () {
        var drinkCount = this.options[this.selectedIndex].text;
        var drinkCountHtml="";
        for (var i = 1; i <= drinkCount; i++) {
            var drinkID = "#Drink" + i+" input";
            if ($(drinkID).length) {
                var drinkName = $(drinkID).val();
                drinkCountHtml += "<div id='Drink" + i + "'><label>Drink: " + i + "</label><input name='Drink"+i+"' type='text' value='"+drinkName+"'></div>";
            }
            else {
                drinkCountHtml += "<div id='Drink" + i + "'><label>Drink: " + i + "</label><input name='Drink"+i+"' type='text'></div>";
            }
        }
        $("#drinks").html(drinkCountHtml);
    });
})