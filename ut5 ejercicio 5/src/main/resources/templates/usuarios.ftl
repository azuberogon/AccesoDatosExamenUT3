<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container mt-5">
            <h2>Listado de Usuarios</h2>

            <#if usuarios?has_content && usuarios?size gt 0>
                <table class="table table-bordered">
                    <thead>
                        <tr style="background-color: #18406a; color: #fff;">
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Saldo</th>
                            <th>Enlace</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list usuarios as usuario>
                            <#assign colorFondo = '#f9f9f9'>
                            <#if usuario_index % 2 == 0>
                                <#assign colorFondo = '#ffffff'>
                            </#if>
                            <tr style="background-color: ${colorFondo};">
                                <td>${usuario.id}</td>
                                <td>${usuario.fullName}</td>
                                <td>${usuario.saldo}</td>
                                <td><a href="/articulos/${usuario.id}">COMPRARLE ALGO</a></td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            <#else>
                <p>Oh... parece que no existen usuarios.</p>
            </#if>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>