<html class="no-js" lang=""> 
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Inscription</title>
    <meta name="description" content="Inscription">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="https://i.imgur.com/QRAUqs9.png">
    <link rel="shortcut icon" href="https://i.imgur.com/QRAUqs9.png">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/cs-skin-elastic.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>

</head>
<body class="bg-dark">

    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="login-logo">
                    <a>
                        <img class="align-content" src="../images/" alt="">
                    </a>
                </div>
                <div class="login-form">
                    <div class="text-center">
                        <strong class="card-title">Inscription</strong>
                    </div>
                    <form id="inscriptionForm">
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" placeholder="Email" id="mail" name="mail" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" placeholder="Password" id="motDePasse" name="motDePasse" required="">
                        </div>

                        <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30">S'inscrire</button>
                        <div class="register-link m-t-15 text-center">
                            <p>Vous avez d�j� un compte ? <a href="<%= request.getContextPath() %>/LoginServlet"> Connectez vous ici</a></p>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/main.js"></script>

    <script>
        const form = document.getElementById('inscriptionForm');

        form.addEventListener('submit', (event) => {
            event.preventDefault(); // Emp�che le rechargement de la page

            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());
            
            console.log(data);
            
         

            fetch('<%= request.getContextPath() %>/InscriptionServlet?' + new URLSearchParams(data).toString(), {
                method: 'POST'
            })
            .then(response => response.json())
            .then(data => {
                console.log('Succ�s:', data);
                alert(data.message);
                if (data.code === 200) {
                    // Redirection vers la page souhait�e
                    window.location.href = '<%= request.getContextPath() %>/LoginServlet'; // Remplacez par l'URL de votre page
                }
                // Afficher un message de succ�s � l'utilisateur
            })
            .catch(error => {
                console.error('Erreur:', error);
                alert('Erreur lors de la cr�ation de votre compte');
                // Afficher un message d'erreur � l'utilisateur
            });
        });
    </script>

</body>
</html>
