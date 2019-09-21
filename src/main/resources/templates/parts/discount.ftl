<script>
    // Set the date we're counting down to
    /*
        let countDownDate = new Date("Dec 31, 2018 23:59:59").getTime();

        // Update the count down every 1 second
        let x = setInterval(function () {

            // Get todays date and time
            let now = new Date().getTime();

            // Find the distance between now and the count down date
            let distance = countDownDate - now;

            // Time calculations for days, hours, minutes and seconds
            let days = Math.floor(distance / (1000 * 60 * 60 * 24));
            let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
            let seconds = Math.floor((distance % (1000 * 60)) / 1000);

            // Output the result in an element with id="demo"
            document.getElementById("demo").innerHTML = days + "d " + hours + "h "
                    + minutes + "m " + seconds + "s ";

            // If the count down is over, write some text
            if (distance < 0) {
                clearInterval(x);
                document.getElementById("demo").innerHTML = "TIME IS OUT!";
            }
        }, 1000);   */
</script>
<div style="background-color: #f3f3f3">
    <div class="p-2"><h3>Best discounts</h3></div>
    <div class="">
        <div class="row">
            <#list producttypeswithdisk as producttype>
                <div class="col-md-3 mt-4">
                    <div class="card chooseCard text-center">
                        <#if producttype.filename??>
                            <div class="wrapper img-wrap img-height">
                                <a href="/products/${producttype.id}">
                                    <img class="card-img-top" src="/img/${producttype.filename}" alt="Card image cap">
                                </a>
                            </div>
                        </#if>
                        <div class="card-body">
                            <h5 class="card-title">${producttype.name}</h5>
                            <p style="color:red;">
                                <del style="text-decoration:line-through">${producttype.price} $</del>
                                (-${producttype.discount}%)
                                <br>
                            <h5>${producttype.price * (100 - producttype.discount) / 100} $</h5>
                            </p>
                            <hr class="hr">
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>