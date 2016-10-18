# Proyecto-IPS-Lab1

## Notas sobre el trabajo:

	Codigo: # + 3 números generados aleatoriamente

### IDs de agentes que utilizan la aplicación:
	- Usuarios: `nombre[3 primeras letras] + apellido[3 primeras letras] + # + codigo`
	
### Organización de las estanterías:
![Imagen estantería](http://i.imgur.com/naJ0355.png?raw=true "Almacen")

	- Supongamos un almacén de 3 estanterías, la situación se pone así
	
	- En la imagen tenemos 3 estanterías: A, B, C. Con 5 espacios cada uno para productos. Todas las estanterías tienen la cara anterior (izquierda) y la cara posterior (derecha)
	- De esta forma, indicamos la siguiente simbología para un objeto PosiciónEstantería:
		- XYZ: 
			- X es el número de la estantería, contando desde la izquierda a la derecha
			- Y: la cara, 0, para anterior, 1 para posterior
			- Z: La sección, dentro de esa cara