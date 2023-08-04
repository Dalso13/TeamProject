
<style>
    #logo img{
        display: inline-block;
        width: 300px;
        cursor: pointer;
        margin-top: 20px;
    }
</style>
<span id="logo"><img src="../../../resources/image/logo.jpg" /></span>
<script>
    document.getElementById("logo").onclick = function () {
        location.href = "/";
    }
</script>