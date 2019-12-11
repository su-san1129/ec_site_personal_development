/**
 * 
 */
var app = new Vue({
	el : '#app',
	data : {
		message : 'Hello Vue.js!',
		val : 'M',
		topping : [],
		quantity : '1',
		price_m: document.getElementById('price_m').value,
		price_l: document.getElementById('price_l').value
	},
	computed : {
		sum : function() {
			if (this.val == 'M') {
				return this.price_m * this.quantity+ (this.topping.length * 200);
			} else {
				return this.price_l * this.quantity + (this.topping.length * 300);
			}
		}
	},
	created: function(){
		console.log('created');
	}
	
});