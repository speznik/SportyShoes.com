const products=[
  {id:1,name:'Sporty Runner',desc:'Lightweight running shoe with breathable mesh',price:89.99,image:'/images/shoe1.jpg'},
  {id:2,name:'Court Champ', desc:'Durable tennis shoe with reinforced toe',      price:99.99,image:'/images/shoe2.jpg'},
  {id:3,name:'Trail Blazer', desc:'Off‑road trail shoe with rugged sole',         price:109.99,image:'/images/shoe3.jpg'},
  {id:4,name:'Gym Flex',    desc:'Versatile gym shoe for all workouts',           price:79.99,image:'/images/shoe4.jpg'},
  {id:5,name:'Sneaker Pro', desc:'Stylish everyday sneaker with comfort fit',     price:69.99,image:'/images/shoe5.jpg'},
  {id:6,name:'Ultra Sprint',desc:'High‑performance sprint shoe with carbon plate',price:119.99,image:'/images/shoe6.jpg'}
];

function renderCatalog(){
  const container=document.getElementById('product-container');if(!container)return;
  container.innerHTML='';
  products.forEach(p=>{
    const card=document.createElement('div');card.className='card';
    card.innerHTML=`
      <img src="${p.image}" alt="${p.name}"/>
      <div class="card-content">
        <h2>${p.name}</h2>
        <p>${p.desc}</p>
        <span class="price">$${p.price.toFixed(2)}</span>
        <button class="btn" onclick="viewProduct(${p.id})">View</button>
        <button class="btn btn-secondary" onclick="addToCart(${p.id})">Add to Cart</button>
      </div>`;
    container.appendChild(card);
  });
}

function renderProductDetail(){
  const container=document.getElementById('product-detail');if(!container)return;
  const id=parseInt(location.pathname.split('/').pop());
  const p=products.find(x=>x.id===id);if(!p)return;
  container.innerHTML=`
    <img src="${p.image}" alt="${p.name}" />
    <div class="details">
      <h2>${p.name}</h2>
      <p>${p.desc}</p>
      <span class="price">$${p.price.toFixed(2)}</span>
      <button class="btn btn-secondary" onclick="addToCart(${p.id})">Add to Cart</button>
      <button class="btn" onclick="location.href='/catalog'">Back to Catalog</button>
    </div>`;
}

function getCart(){return JSON.parse(localStorage.getItem('cart')||'[]');}
function saveCart(cart){localStorage.setItem('cart',JSON.stringify(cart));}
function addToCart(id){const cart=getCart();cart.push(id);saveCart(cart);alert('Added to cart');}
function removeFromCart(idx){const cart=getCart();cart.splice(idx,1);saveCart(cart);renderCart();}
function renderCart(){const container=document.getElementById('cart-container');if(!container)return;
  const cart=getCart();container.innerHTML='';
  if(!cart.length){container.innerHTML='<p>Your cart is empty.</p>';return;}
  cart.forEach((id,idx)=>{
    const p=products.find(x=>x.id===id);
    const row=document.createElement('div');row.className='cart-item';
    row.innerHTML=`<span>${p.name}  $${p.price.toFixed(2)}</span>
                   <button class="btn remove-btn" onclick="removeFromCart(${idx})">Remove</button>`;
    container.appendChild(row);
  });
}

function initSignup(){const form=document.getElementById('signup-form');if(!form)return;
  form.addEventListener('submit',e=>{e.preventDefault();alert('Account created (mock)!');location.href='/catalog';});}

function initCheckout(){const form=document.getElementById('checkout-form');if(!form)return;
  form.addEventListener('submit',e=>{e.preventDefault();alert('Order placed!');localStorage.removeItem('cart');location.href='/catalog';});}

document.addEventListener('DOMContentLoaded',()=>{
  const path=location.pathname;
  if(path==='/'||path.startsWith('/catalog'))      renderCatalog();
  else if(path.startsWith('/product'))             renderProductDetail();
  else if(path.startsWith('/cart'))                renderCart();
  else if(path.startsWith('/signup'))              initSignup();
  else if(path.startsWith('/checkout'))            initCheckout();
});
