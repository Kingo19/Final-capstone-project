<template>
  <footer class="main-footer" :style="'background-image:url(' + backgroundImage + ')'">
    <div class="auto-container">
      <!--        <div class="logo">-->
      <!--          <a href="index.html"><img src="images/footer-logo.png" alt=""></a>-->
      <!--        </div>-->
      <div class="footer-nav">
        <a href="#">Home</a>
        <a href="#">Recipes</a>
        <a href="#">About us</a>
        <a href="#">Events</a>
        <a href="#">Blog</a>
        <a href="#">Contacts</a>
      </div>
      <ul class="social-box">
        <li><div class="social-icon"><a href="#"><span class="material-icons">bookmark</span></a></div></li>
        <li><div class="social-icon"><a href="#"><span class="material-icons">facebook</span></a></div></li>
        <li><div class="social-icon"><a href="#"><span class="material-icons">instagram</span></a></div></li>
        <li><div class="social-icon"><a href="#"><span class="material-icons">play_circle_outline</span></a></div></li>
      </ul>
      <!--        <div class="copyright">
                © All Right Reserved {{ currentYear }}
              </div>-->
    </div>
  </footer>
</template>

<script>
export default {

};
</script>

<style>
.main-footer {
  position: relative;
  text-align: center;
  padding: 10px 0px 0px;
  /*    margin-top: 300px;
  changed padding to 10px*/
  background: #ffffff no-repeat center bottom;
}

.auto-container {
  position: static;
  max-width: 1200px;
  padding: 0px 15px;
  margin: 0 auto;
}

.main-footer{
  position: relative;
}

.main-footer .footer-nav {
  position: relative;
  margin: 25px 0px 30px;
  display: flex;
  justify-content: center;
}

.main-footer .footer-nav a {
  color: rgb(0, 0, 0);
  font-size: 30px;
  margin: 0 15px;
  text-decoration: none;
}

.main-footer .social-box {
  position: relative;
  list-style: none;
  display: flex;
  align-items: center;
  justify-content: center;
}

.main-footer .social-box li {
  margin: 0 10px;
}

.main-footer .social-box .social-icon {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: rgb(207, 201, 201);
}

.main-footer .social-box a {
  font-size: 24px;
  color: #1c1c1c;
  text-decoration: none;
}

.main-footer .copyright {
  position: relative;
  margin-top: 60px;
  color: #1c1c1c;
  font-size: 15px;
  padding: 25px 0px;
  border-top: 1px solid #e6e3e2;
}

/*




  !* Footer styles *!
  footer {
    background-color: #333;
    color: #fff;
    padding: 1rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .contact-info p {
    margin: 0;
  }

  .footer-menu ul {
    list-style: none;
    display: flex;
  }

  .footer-menu li {
    margin-right: 20px;
  }

  .footer-menu a {
    text-decoration: none;
    color: #fff;
  }*/
</style>