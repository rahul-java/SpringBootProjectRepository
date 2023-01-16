
const toggleSidebar = () => {
	
    if($(".sidebar").is(":visible"))
      {
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%");
      }
     else
       {
         $(".sidebar").css("display", "block");
         $(".content").css("margin-left" ,"20%");   
       }
};


//Searching Module Function


const search=()=>{
  //console.log("searching....");
let query=$("#search-input").val();
if(query=="")
{
  $(".search-result").hide();
}
else{
  //sending req to server.
	
	let url=`http://localhost:8888/search/${query}`;
  fetch(url).then((response)=>{
   return response.json();
  })
  .then((data)=>{
    //access data
    console.log(data);
    let text =`<div class='list-group'>`
    
      data.forEach((c) => {
        text +=`<a href='/user/${c.cid}/contact' class='list-group-item list-group-item-action'>${c.name} </a>`
      });

    text +=`</div>`

    $(".search-result").html(text);
    $(".search-result").show();
  });
}
};

//Payment Module Function

const paymentStart =() => {
  console.log("Payment Started...");
  let amount = $("#amount").val();
  console.log(amount);
  if(amount==""|| amount==null)
  {
	swal("Failed!", "Amount is required !", "error");
    
    return ;
  }
  
  //ajax 
  
  $.ajax(
	{
      url:'/user/create_order',
      data:JSON.stringify({ amount: amount, info: 'order_request'}),
      contentType:'application/json',
      type:'POST',
      dataType:'json',
      success:function(response){
	
	if(response.status=='created')
	{
		//open payment form
		let options={
			key: "rzp_live_7m44W5uO4Bbit3",
			amount: response.amount,
			currency: "INR",
			name: "Smart Contact Manager",
			description: "Donation",
			image: "https://cdn-icons-png.flaticon.com/512/1019/1019607.png",
			order_id: response.id,
			handler:function(response){
				console.log("Payment Successful");
				console.log(response.razorpay_payment_id);
				console.log(response.razorpay_order_id);
				console.log(response.razorpay_signature);
				
			updatePaymentOnServer(response.razorpay_payment_id,response.razorpay_order_id,"paid");
			},
		prefill: {
                  name: "",
                  email: "",
                  contact: ""
                 },
        notes: {
                 address: "SmartContactManager"
                },
        theme: {
              color: "#3399cc"
              }
		};
	
	let rzp=new Razorpay(options);
	
	rzp.on("payment.failed",function(response){
		
		console.log(response.error.code);
		console.log(response.error.description);
		console.log(response.error.source);
		console.log(response.error.step);
		console.log(response.error.reason);
		console.log(response.error.metadata.order_id);
		console.log(response.error.metadata.payment_id);
		alert("OOPs Payment Failed!!!");
		swal("Failed!", "OOPs Payment Failed!!!", "error");
	});
	
	rzp.open();
	
	}
        //invoked when success.
        console.log(response)
   },
      error:function(error){
        //invoke when error.
        console("something went wrong"+error)
      },
    });
  };
  
 function updatePaymentOnServer(payment_id,order_id,status)
{
  $.ajax({
    url:'/user/update_order',
    data:JSON.stringify({ payment_id: payment_id,order_id: order_id,status: status }),
    contentType:'application/json',
    type:'POST',
    dataType:'json',
    success:function(response){
      swal("Good job!", "congrates ! payment successful", "success");
    },
    error:function(error){
      swal("Failed!", "Your payment is successful but we did not get it on server, we will contact you as soon as possible", "error");
    }
  })
}
//send req to server

//jquery ajax




