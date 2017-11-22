/**
 * 
 * @authors Sheng Jie
 * @date    2016-10-19 10:56:22
 */

function addEventSimple(obj,evt,fn){
	if(obj.addEventListener){
		obj.addEventListener(evt,fn,false);
	}
	else if(obj.attachEvent){
		obj.attachEvent('on'+evt,fn);
	}
}

var scrollingBox;
var scrollingInterval;
var reachedBottom=false;
var bottom;

function initScrolling(){
	scrollingBox=document.getElementById("demo");
	scrollingBox.style.overflow="hidden";
	scrollingInterval=setInterval("my_scroll()",30);
	scrollingBox.onmouseover=over;
	scrollingBox.onmouseout=out;
}

function my_scroll(){
	var origin=scrollingBox.scrollTop++;
	if(origin==scrollingBox.scrollTop){
		if(!reachedBottom){
			scrollingBox.innerHTML+=scrollingBox.innerHTML;
			reachedBottom=true;
			bottom=origin;
		}
		else{
			scrollingBox.scrollTop=bottom;
		}
	}
}

function over(){
	clearInterval(scrollingInterval);
}

function out(){
	scrollingInterval=setInterval("my_scroll()",30);
}

addEventSimple(window,'load',initScrolling);