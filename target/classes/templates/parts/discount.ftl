<script>
    // Set the date we're counting down to
<<<<<<< HEAD
    var countDownDate = new Date("Dec 31, 2018 23:59:59").getTime();

    // Update the count down every 1 second
    var x = setInterval(function () {

        // Get todays date and time
        var now = new Date().getTime();

        // Find the distance between now and the count down date
        var distance = countDownDate - now;

        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Output the result in an element with id="demo"
        document.getElementById("demo").innerHTML = days + "d " + hours + "h "
                + minutes + "m " + seconds + "s ";

        // If the count down is over, write some text
        if (distance < 0) {
            clearInterval(x);
            document.getElementById("demo").innerHTML = "TIME IS OUT!";
        }
    }, 1000);
</script>
<div style="background-color: #f3f3f3">
    <div class="d-flex flex-row  mb-3">
        <div class="p-2"><h1>Daily deal!</h1></div>
        <div class="p-2"><h1 id="demo"></h1></div>
    </div>
=======
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
>>>>>>> 1ce13d41b9b87894c8cc897632d82249e7d777af
    <div class="">
        <div class="row">
            <#list producttypeswithdisk as producttype>
                <div class="col-md-3 mt-4">
<<<<<<< HEAD
                    <div class="card chooseCard text-center img">
                        <#if producttype.filename??>
                            <div class="wrapper">
=======
                    <div class="card chooseCard text-center">
                        <#if producttype.filename??>
                            <div class="wrapper img-wrap img-height">
>>>>>>> 1ce13d41b9b87894c8cc897632d82249e7d777af
                                <a href="/products/${producttype.id}">
                                    <img class="card-img-top" src="/img/${producttype.filename}" alt="Card image cap">
                                </a>
                            </div>
                        </#if>
                        <div class="card-body">
                            <h5 class="card-title">${producttype.name}</h5>
                            <p style="color:red;">
                                <del style="text-decoration:line-through">${producttype.price} $</del>
<<<<<<< HEAD
                            (-${producttype.discount}%)
                            <br> <h5>${producttype.price * (100 - producttype.discount) / 100} $</h5>
=======
                                (-${producttype.discount}%)
                                <br>
                            <h5>${producttype.price * (100 - producttype.discount) / 100} $</h5>
>>>>>>> 1ce13d41b9b87894c8cc897632d82249e7d777af
                            </p>
                            <hr class="hr">
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>