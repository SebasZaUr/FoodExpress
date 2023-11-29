var cart=[];

function displaycart(a){
    let j=0;
    if(cart.length==0){
        document.getElementById('cartItem').innerHTML = "Your cart is empty”;
    }
    else{
        document.getElementById('cartItem').innerHTML = cart.map((items)=>
        {
            var {image, title, price} = items;
            return(
               `<div class='cartItem'>
                <div> class='row-img'>
                    <img class='rowimg' src=${image}>
                </div>
                <p style='font-size:12px;'>${title}</p>
                <h2 style='font-size:15px;'>${price}.00</h2>`+
                "<i class='fa-solid fa-trash' onclinc='delElement("+ (j++) +")'></i></div>"


            )
        }).join('');

    }


