
window.onload =function(){ 
      var oZT = document.getElementById('ZT');
      var aXZ = document.getElementById('XuanZ').getElementsByTagName('li');
      var oXK = document.getElementById('XK');
      var oXK02 = document.getElementById('XK02');
      var oXK03 = document.getElementById('XK03');
      var aImg01 = document.getElementById('li01').getElementsByTagName('img');
      var aImg02 = document.getElementById('li02').getElementsByTagName('img');
      var aImg03 = document.getElementById('li03').getElementsByTagName('img');

     aXZ[0].onclick = function(){
        oZT.style.display='block';
        oXK.style.display='none';
        aImg01[0].style.display='block';
        aImg01[1].style.display='none';
        oXK02.style.display='none';
        aImg02[0].style.display='block';
        aImg02[1].style.display='none';

     if (oXK03.style.display!='block') {
        oXK03.style.display='block';
        aImg03[0].style.display='none';
        aImg03[1].style.display='block';
      }
      else{
        oXK03.style.display='none';
        aImg03[0].style.display='block';
        aImg03[1].style.display='none';
      }
      
     }
     aXZ[1].onclick = function(){
      oZT.style.display='block';
      oXK03.style.display='none';
        aImg03[0].style.display='block';
        aImg03[1].style.display='none';
        oXK02.style.display='none';
        aImg02[0].style.display='block';
        aImg02[1].style.display='none';
      if (oXK.style.display!='block') {
        oXK.style.display='block';
        aImg01[0].style.display='none';
        aImg01[1].style.display='block';
      }
      else{
        oXK.style.display='none';
        aImg01[0].style.display='block';
        aImg01[1].style.display='none';
      }
     }
     aXZ[2].onclick = function(){
       oZT.style.display='block';
       oXK03.style.display='none';
       aImg03[0].style.display='block';
       aImg03[1].style.display='none';
       oXK.style.display='none';
       aImg01[0].style.display='block';
       aImg01[1].style.display='none';
      if (oXK02.style.display!='block') {
        oXK02.style.display='block';
        aImg02[0].style.display='none';
        aImg02[1].style.display='block';
      }
      else{
        oXK02.style.display='none';
        aImg02[0].style.display='block';
        aImg02[1].style.display='none';
      }
     }
     /*点击空白处，隐藏下拉菜单*/
     oZT.onclick = function(){
      oZT.style.display='none';
      oXK.style.display='none';       
        aImg01[0].style.display='block';
        aImg01[1].style.display='none';
        oXK02.style.display='none';
        aImg02[0].style.display='block';
        aImg02[1].style.display='none';
        oXK03.style.display='none';
        aImg03[0].style.display='block';
        aImg03[1].style.display='none';
     }
}