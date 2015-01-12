Track e servico para GMrio

================================================================
			Cadastrar Pessoa ( entities/create ) :

name : 
type : AUTOMOBILE, TRUCK, WATERCRAFT, PERSON.

Assim que cadastrar receberá o id.(entityIds)

Request:
{
    "entities": [
        {
            "name": "Ford Fiesta 001",
            "type": "AUTOMOBILE"
        },
        {
            "name": "Chevrolet Volt 001",
            "type": "AUTOMOBILE"
        },
        {
            "name": "Chevrolet Volt 002",
        }
    ]
}


Response:
{
    "entityIds": [
        "ec6053f142ade5c9",
        "1ff3a55f94e954ee",
        "fb061e749fec1627"
    ]
}



============================================================
		Listar Pessoas ( entities/list )

envia(Request) :
{ "minId": "1ff3a55f94e954ee"}

mindId -- minimo id e os proximos 256 Ids ou entityId -- pessoa daquele id

recebe(Response) : 
{
    "entities": [
        {
            "id": "1ff3a55f94e954ee",
            "name": "Chevrolet Volt 001",
            "type": "AUTOMOBILE"
        },
        {
            "id": "ec6053f142ade5c9",
            "name": "Ford Fiesta 001",
            "type": "AUTOMOBILE"
        }
    ]
}

===============================================================
		Remover Pesssoa ( entities/delete ):
request : 
{
    "entityIds": [
        "df512d2fcaa60840",
        "651d0085265a3a9f"
    ]
}

response : 
{}

===============================================================
		Criando Grupo (collections/create) 

Request:

{
    "collections": [
        {
            "name": "SEA rental fleet - compact"
        },
        {
            "name": "SEA rental fleet - midsize"
        }
    ]
}

Response:

{
    "collectionIds": [
        "0eb06d476f8a7486",
        "0179f339ec9232ef"
    ]
}




================================================================
		Adicionando Pessoa no Grupo (collections/addentities):

{
    "collectionId": "0eb06d476f8a7486",
    "entityIds": [
        "1ff3a55f94e954ee",
        "ec6053f142ade5c9"
    ]
}

=================================================================
		  Removendo Pessoa do Grupo (collections/removeentities)

{
    "collectionId": "0eb06d476f8a7486",
    "entityIds": [
        "46941afc7c262ec4",
        "0291c259ca933c28"
    ]
}


====================================================================
			Listando Grupo (collections/list) :

Request:

{
    "minId": "0eb06d476f8a7486"
}

minId ou collectionIds.

Response:
{
    "collections": [
        {
            "entityIds": [
                "1ff3a55f94e954ee",
                "ec6053f142ade5c9"
            ],
            "id": "0eb06d476f8a7486",
            "name": "SEA rental fleet - compact"
        }
    ]
}


=========================================================================
					Deletando Grupo(collections/delete):
{
    "collectionIds": [
        "0979f339ec923298"
    ]
}

=========================================================================
					CRUMBS
					
propriedades : 

 location : um objeto que consiste em propriedades numéricas lat e lng. Este deve ser especificado quando a gravação migalhas. 
 
 timestamp : O tempo em que o local foi medido. Este deve ser especificado quando a gravação migalhas. Timestamps deve ser expresso em segundos 
desde 1970-01-01 00:00:00 UTC, ou seja, tempo POSIX.
Timestamps são expressos como números de ponto flutuante, para que eles possam representar frações de segundo
 
 confidenceRadius: Uma medida inversamente correlacionada de a precisão da medição de localização. 
Isso deve ser definido para o raio em metros de um intervalo de confiança de 95% ao redor do ponto de localização, se disponível, 
ou omitido contrário. Se estiver presente, ele deve ser positiva e não mais do que 35 km. 
 
 heading: em graus cabeçalho da entidade. Os títulos são expressos em graus no sentido horário do norte dentro do intervalo [0, 360).
Este valor deve ser omitida se for desconhecido.
 
 userData:  Um objeto contendo arbitrária chaves string e valores de seqüência, para uso pelo usuário de forma específica da aplicação. 
Isto pode ser omitido. 
 
{
    "confidenceRadius": 3.14,
    "location": {
        "lat": -33.866495,
        "lng": 151.195446
    },
    "timestamp": 1341375062.19,
    "userData": {
        "driver_name": "Joe",
        "measured_vehicle_speed": "110.2"
    }
}


=============================================================================================================================
				Gravando Crumbs (crumbs/record)
				
Request:

{
    "crumbs": [
        {
            "confidenceRadius": 3.14,
            "location": {
                "lat": -33.866495,
                "lng": 151.195446
            },
            "timestamp": 1341375062.19,
            "userData": {
                "driver_name": "Joe",
                "measured_vehicle_speed": "110.2"
            }
        }
    ],
    "entityId": "1ff3a55f94e954ee"
}


Response: {}

=========================================================================================================================
			Buscando por Grupo de Pessoas Crumbs (crumbs/getrecent)

			
Request:
{
    "collectionId": "0eb06d476f8a7486"
}

Response:

{
    "results": [
        {
            "crumb": {
                "confidenceRadius": 3.14,
                "location": {
                    "lat": 17.19,
                    "lng": 23.31
                },
                "timestamp": 1341375062.19,
                "userData": {
                    "driver_name": "Joe",
                    "measured_vehicle_speed": "110.2"
                }
            },
            "entityId": "1ff3a55f94e954ee"
        }
    ]
}

====================================================================================================
			Buscando Historico do Crumbs

entityId : Obrigatorio
timestamp : Obrigatorio
countBefore: Opcional. Numero antes do timestamp indicado
countAfter:  Opcional.

As ultimas 25 Crumbs antes do momento em particular, juntamente com o crumbs para o tempo exato:

Request:
{
    "entityId": "1ff3a55f94e954ee",
    "timestamp": 1341500000,
    "countBefore": 25
}

Response:
{
    "crumbs": [
        {
            "confidenceRadius": 3.14,
            "location": {
                "lat": 43.0,
                "lng": -108.0
            },
            "timestamp": 1341500000.0,
            "userData": {
                "driver_name": "Joe",
                "measured_vehicle_speed": "110.2"
            }
        }
    ]
}