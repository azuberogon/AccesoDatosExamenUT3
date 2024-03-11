<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Listado de Artículos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container mt-5">
            <h2>Listado de Artículos</h2>
            <#if articulos?has_content && articulos?size gt 0>
                <table class="table table-bordered">
                    <thead>
                        <tr style="background-color: #18406a; color: #fff;">
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Valor</th>
                            <th>Propietario</th>
                            <#if mostrarIdExtra>
                                <th>Enlace compra</th>
                            </#if>
                        </tr>
                    </thead>
                    <tbody>
                        <#list articulos as articulo>
                            <#assign colorFondo = '#f9f9f9'>
                            <#if articulo_index % 2 == 0>
                                <#assign colorFondo = '#ffffff'>
                            </#if>
                            <tr style="background-color: ${colorFondo};">
                                <td>${articulo.id}</td>
                                <td>${articulo.nombre}</td>
                                <td>${articulo.valor} €</td>
                                <td>${articulo.idPropietario}</td>
                                <#if mostrarIdExtra>
                                    <td><a href="/comprar/${articulo.id}">comprar</a></th>
                                </#if>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            <#else>
                <p>Oh... parece que no dispone de articulos.</p>
            </#if>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>