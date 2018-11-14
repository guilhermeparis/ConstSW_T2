# Trabalho de Construcao de Software

## Resumo
  Framework que disponibiliza as operações básicas de um CRUD em cima de um banco de dados relacional (MySQL, Oracle), 
  através de uma classe abstrata onde são implementados tais operações e uma UI onde é possivel realizar tais operações.
	
## Configuração do banco de dados
  O usuário já deve estar com o banco relacional escolhido rodando na máquina em questão. Então deve-se implementar uma classe que estenda a classe "SqlConnection" e implemente o método "CreateConnection", como foi feito na classe "MySqlConnection" da aplicação de exemplo.
	
	```
	@Override
	public Connection createConnection() {

		try {
			conn = DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Retorna a conexao aberta
		return conn;
	}
	
	```
	
  Isso visa disponibilizar à classe "SqlConnection" o atributo "conn" que servirá para realizar as operações com o  banco de dados.
  Obs: É importante verificar a porta em que o seu banco de dados está usando e colocar a mesma no código. Por exemplo, no MySql a default é 3306.
		
## Como as tabelas são representadas
  O Framework conta com uma classe chamada "TableObject" que serve para representar um mapeamento entra a tabela no banco de dados com o código em si.
  Toda tabela do banco, em que o usuário queria realizar operações sobre ela, deve ter uma classe estendida de "TableObject" para representar a mesma.
  A classe "TableObject" exige que toda tabela tenha um campo ID, isso garante que o framework trabalhe com um objeto uníco e não tenha repetições.
		
  As colunas do banco são representadas por atributos, e as chaves primárias e extrangerias da tabela devem ser adicionados aos arrays 
  "primaryKey" e "foreignKey", respectivamente, no construtor além disso o array "relations" serve para declarar relações N para N. Conforme é realizado na classe "Carro" da aplicação de exemplo:
	
	```
		public Carro(String marca,String modelo,Double motor, int concessionariaId)
	{
		super();
		
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
		this.concessionariaId = concessionariaId;
		
		primaryKey.add("id");
		setforeignKey("concessionariaId","Concessionaria","id");
		relations.add(new CarroConcessionaria(id, 0, 0));
	}
	
	```
	
  No array "primaryKey" deve ser adicionado o atributo no qual é uma chave primária no banco de dados. 
  Já para o array "foreignKey", deve-se utilizar o método "setforeignKey" para facilitar a inserção do mesmo. Deve ser passado o atributo na classe que é uma chave estrangeira, a outra classe na qual ele aponta e por fim o atributo na outra classe. Desse módo é feito a ligação entre a coluna da sua tabela e a coluna da outra tabela.
	
  Além disso, toda classes que representam tabelas devem implementar os seguintes métodos: 
	
	* setProperties(Map<String, Object> dict)
	* Map<String, Object> convertToDict()
	
  Toda a comunicação entre as operações com o banco de dados, UI e os objetos de mapeamento é realizado através de dicionários para facilitar a manipulação de objetos.
  Assim o "setProperties" é responsável por transformar atributos de um dicionário para a classe em si e o "convertToDict" realiza o contrário, transformando a classe em si para dentro de um dicionário.
	
  ATENÇÃO: a realização das operações com sucesso depende da boa implementação desses dois métodos. Veja o seguinte exemplo:
  
  ```
	@Override
	public void setProperties(Map<String, Object> dict) {
		setId(Integer.parseInt(dict.get("id").toString()));
		marca = dict.get("marca").toString();
		motor =  (Double.parseDouble(dict.get("motor").toString()));
		concessionariaId = Integer.parseInt(dict.get("concessionariaId").toString());
		modelo = dict.get("modelo").toString();
	}

	@Override
	public Map<String, Object> convertToDict() {
		Map<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("id", id);
		obj.put("marca", marca);
		obj.put("modelo", modelo);
		obj.put("motor", motor);
		obj.put("concessionariaId", concessionariaId);
		return obj;
	}
	
```

## Main
  O usuário do framework podera se basear na classe "Main" da aplicação de exemplo para visualizar como instanciar a aplicação.
  Deve ficar claro que o usuário devera criar objetos como exemplo das classes em que ele quiser manipular e inserir as mesmas em um array que sera passado para a classe "CrudFramework", conforme aplicação de exemplo disponível.

## UI
  A UI conta com um form que disponibiliza as 4 operações do CRUD, em cada uma é possivel realizar a sua respectiva função.
  A primeira coisa a ser feita é atraves do combobox escolher em qual tabela sera feita as operações.
  
  O unico detalhe é a parte do "Create" onde se o objeto em questão tiver um relacionamento após a tentativa de inclusão do mesmo sera     apresentado um novo form de inclusão para adicionar os campos referentes a tabela de relacionamento.

  Na parte do "Update" o usuário tera que primeiro inserir o "ID" do objeto, dar um "GET" para trazer as informações do mesmo na tela e após isso realizar as alterações que ele desejar nos campos apresentados.
  
  O "GET" traz o objeto a partir do ID inserido e o "delete" remove o objeto a partir do ID também.
	
	

