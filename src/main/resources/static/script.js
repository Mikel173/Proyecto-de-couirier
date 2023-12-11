
// Cargar el grafo al cargar la página
$(document).ready(function () {
  cargarGrafo();
  cargarOpcionesNodos();
});

function cargarOpcionesNodos() {
  // Realizar una solicitud GET al controlador de Spring Boot para obtener los nombres de las ciudades
  $.ajax({
    type: "GET",
    url: "/api/cities",
    success: function (cityNames) {
      llenarSelectOptions(cityNames, "selectOrigen");
      llenarSelectOptions(cityNames, "selectDestino");
    },
    error: function (error) {
      console.error('Error al obtener los nombres de las ciudades:', error);
      console.error('Detalles del error:', error.responseText);
    }
  });
}
function llenarSelectOptions(nodes, selectId) {
  const select = $("#" + selectId);
  select.empty();

  nodes.forEach(node => {
    // Verifica si el nodo es una cadena y úsala directamente, de lo contrario, usa node.cityName
    const cityName = typeof node === 'string' ? node : node.name || "Nombre Desconocido";

    console.log("City Name:", cityName);  // Agrega esta línea para depurar

    const option = $("<option>")
        .val(node.id)
        .text(cityName);
    select.append(option);
  });
}





function cargarGrafo() {
  // Realizar una solicitud GET al controlador de Spring Boot para obtener el grafo
  $.ajax({
    type: "GET",
    url: "/api/graph",
    success: function (graph) {
      // Utiliza la información del grafo (graph) para construir tu grafo en el frontend
      construirGrafo(graph);
    },
    error: function (error) {
      console.error('Error al obtener el grafo:', error);

      // Agregar esta línea para imprimir el error completo en la consola
      console.error('Detalles del error:', error.responseText);
    }

  });
}

function construirGrafo(graph) {
  const svg = d3.select("svg");
  let link = svg.selectAll(".link");
  let node = svg.selectAll(".node");

  const simulation = d3.forceSimulation()
      .force("link", d3.forceLink().id(d => d.id).distance(100))
      .force("charge", d3.forceManyBody().strength(-100))
      .force("center", d3.forceCenter(400, 400))
      .force("collision", d3.forceCollide().radius(30));

  update();

  function update() {
    link = link.data(graph.links);
    link.exit().remove();
    link = link.enter().append("line").attr("class", "link").merge(link);

    node = node.data(graph.nodes, d => d.id);
    node.exit().remove();
    node = node.enter().append("circle").attr("class", "node").attr("r", 20).merge(node);

    simulation.nodes(graph.nodes);
    simulation.force("link").links(graph.links);
    simulation.on("tick", ticked);

    function ticked() {
      link.attr("x1", d => d.source.x)
          .attr("y1", d => d.source.y)
          .attr("x2", d => d.target.x)
          .attr("y2", d => d.target.y);

      node.attr("cx", d => d.x)
          .attr("cy", d => d.y);
    }
  }
}



$(document).ready(function () {
  // Cuando el documento esté listo, cargar los paquetes
  cargarPaquetes();
});

function guardarInformacion() {
  var origen = $("#selectOrigen option:selected").text();
  var destino = $("#selectDestino option:selected").text();

  // Validaciones
  if (!origen || !destino) {
    alert("Seleccione origen y destino");
    return;
  }

  var nuevoId = parseInt($("#inputId").val(), 10);
  var nuevoPeso = $("#inputPeso").val();

  // Asegúrate de que #inputDimensiones tenga un valor definido
  var nuevoDimensiones = $("#inputDimensiones").val() || "Sin dimensiones";

  var nuevoContenido = $("#inputContenido").val();

  var nuevoPaquete = {
    idPaquete: nuevoId,
    origen: origen,
    destino: destino,
    peso: nuevoPeso,
    dimensiones: nuevoDimensiones,
    contenido: nuevoContenido
  };

  console.log("Origen:", origen);
  console.log("Destino:", destino);

  // Enviar una solicitud POST al controlador de Spring Boot
  $.ajax({
    type: "POST",
    url: "/registrarPaquete",
    contentType: "application/json",
    data: JSON.stringify(nuevoPaquete),
    success: function () {
      // Después de guardar el paquete, cargar todos los paquetes y actualizar la tabla
      cargarPaquetes(origen, destino); // Pasar origen y destino como parámetros
    },
    error: function (error) {
      console.error('Error al registrar el paquete:', error);
      alert("Error al registrar el paquete. Consulta la consola para más detalles.");
    }
  });
}
// Modificar la función para recibir los parámetros origen y destino
function cargarPaquetes(origen, destino) {
  // Realizar una solicitud GET al controlador de Spring Boot para obtener todos los paquetes
  $.ajax({
    type: "GET",
    url: "/obtenerPaquetes",
    success: function (paquetes) {
      // Limpiar la tabla antes de agregar nuevas filas
      $("#tablaPaquetesBody").empty();

      // Iterar sobre la lista de paquetes y agregar filas a la tabla
      paquetes.forEach(function (paquete) {
        agregarFilaTabla(paquete, origen, destino);
      });
    },
    error: function (error) {
      console.log(error);
    }
  });
}

function agregarFilaTabla(paquete, origen, destino) {
  // Crear una nueva fila con los datos del paquete para la primera tabla
  var filaPrimeraTabla = "<tr>" +
      "<td>" + paquete.idPaquete + "</td>" +
      "<td>" + (paquete.origen || "Sin origen") + "</td>" +
      "<td>" + (paquete.destino || "Sin destino") + "</td>" +
      "<td>" + paquete.peso + "</td>" +
      "<td>" + paquete.dimensiones + "</td>" +
      "<td>" + paquete.contenido + "</td>" +
      "</tr>";
  // Agregar la fila a la primera tabla
  $("#tablaPaquetesBody").append(filaPrimeraTabla);

  // Crear una nueva fila con los datos del paquete para la segunda tabla
  var filaSegundaTabla = "<tr id='paquete-" + paquete.idPaquete + "'>" +
      "<td>" + paquete.contenido + "</td>" +
      "<td>" + (paquete.origen || "Sin origen") + "</td>" +
      "<td>" + (paquete.destino || "Sin destino") + "</td>" +
      "<td id='distancia-" + paquete.idPaquete + "'>" + 12 + " km</td>" +
      "<td>" +
      "<button class='btn btn-primary' onclick='enviarPaquete(" + paquete.idPaquete + ")'>Enviar</button>" +
      "</td>" +
      "</tr>";
  // Agregar la fila a la segunda tabla
  $(".SelecionarP tbody").append(filaSegundaTabla);


  var filaTerceraTabla = "<tr id='paquete-" + paquete.idPaquete + "'>" +
      "<td>" + (paquete.origen || "Sin origen") + "</td>" +
      "<td>" + (paquete.destino || "Sin destino") + "</td>" +
      "<td id='distancia-" + paquete.idPaquete + "'>" + paquete.distancia+ " km</td>" +
      "<td>" + paquete.contenido + "</td>" +
      "</td>" +
      "</tr>";

// Agregar la fila a la tercera tabla
  $("#tablaEnviosBody").append(filaTerceraTabla);

}


// Esta función se llama al hacer clic en el botón "Enviar" en la segunda tabla
function enviarPaquete(idPaquete) {


  // Obtener los detalles del paquete desde la primera tabla
  var detalleContenido = $("#paquete-" + idPaquete + " td:eq(0)").text();
  var detalleOrigen = $("#paquete-" + idPaquete + " td:eq(1)").text();
  var detalleDestino = $("#paquete-" + idPaquete + " td:eq(2)").text();
  var detalleDistancia = $("#paquete-" + idPaquete + " td:eq(3)").text();

  // Crear un objeto con la información del paquete



  var nuevoEnvio = {
    numeroSeguimiento: generarNumeroSeguimiento(), // Genera un nuevo número de seguimiento
    origen: detalleOrigen,
    destino: detalleDestino,
    distancia: detalleDistancia,
    contenido: detalleContenido
  };

  // Agregar una fila a la tabla en Envios.html
  agregarFilaEnvios(nuevoEnvio);

  // Actualizar los detalles del paquete en la barra de estado
  $("#detalleContenido").text(detalleContenido);
  $("#detalleOrigen").text(detalleOrigen);
  $("#detalleDestino").text(detalleDestino);
  $("#detalleDistancia").text(detalleDistancia);

  // ... Puedes realizar acciones adicionales aquí, como hacer una solicitud Ajax al servidor para obtener más detalles
}


function enviarPaquete(idPaquete) {
  // Obtener los detalles del paquete desde la primera tabla
  var detalleContenido = $("#paquete-" + idPaquete + " td:eq(0)").text();
  var detalleOrigen = $("#paquete-" + idPaquete + " td:eq(1)").text();
  var detalleDestino = $("#paquete-" + idPaquete + " td:eq(2)").text();
  var detalleDistancia = $("#paquete-" + idPaquete + " td:eq(3)").text();

  // Actualizar los detalles del paquete en la barra de estado
  $("#detalleContenido").text(detalleContenido);
  $("#detalleOrigen").text(detalleOrigen);
  $("#detalleDestino").text(detalleDestino);
  $("#detalleDistancia").text(detalleDistancia);

  // ... Puedes realizar acciones adicionales aquí, como hacer una solicitud Ajax al servidor para obtener más detalles

  // Realizar la animación de encendido de nodos
  animarNodos();
}

function animarNodos() {
  // Obtener los nodos específicos que deseas animar (1, 3, 5, 6)
  const nodosAnimar = d3.selectAll(".node").filter(function (d) {
    return [1, 3, 5, 6].includes(d.id);
  });

  // Iniciar la animación para cada nodo en el intervalo de tiempo
  nodosAnimar.each(function (d, i) {
    const nodo = d3.select(this);
    setTimeout(() => encenderNodo(nodo), i * 1200);
  });
}

function encenderNodo(nodo) {
  const estadoBarra = $(".BarraEstado p");
  let mensajeEstado = "";

  switch (nodo.datum().id) {
    case 1:
      mensajeEstado = "Partiendo";
      break;
    case 3:
    case 5:
      mensajeEstado = "En Tránsito";
      break;
    case 6:
      mensajeEstado = "Llegando";
      // Mostrar alerta cuando el nodo 6 esté cambiando de color
      setTimeout(() => alert("¡El pedido ha llegado!"), 1000);
      break;
    default:
      break;
  }

  // Cambiar el estado en la barra de estado
  estadoBarra.text("Estado: " + mensajeEstado);

  // Cambiar el color del nodo para simular la animación de encendido
  nodo.transition()
      .duration(1000)
      .style("fill", "#f39c12")  // Cambiar a tu color deseado
      .style("stroke", "#f39c12");

  // Puedes agregar más acciones de animación aquí según sea necesario
}






