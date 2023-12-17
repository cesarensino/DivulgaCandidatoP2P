import java.io.*;
import java.net.*;    
import java.util.*;	

public class DivulgaCandidato {
    public static void main(String[] args) throws IOException {
		


        // Criação de pastas de trabalho
        File pastaRecebidos = new File("recebidos");
        if (!pastaRecebidos.exists()) {
            pastaRecebidos.mkdir();
        }
        File pastaProntos = new File("prontos");
        if (!pastaProntos.exists()) {
            pastaProntos.mkdir();
        }
		File pastaArquivados = new File("arquivados");
        if (!pastaProntos.exists()) {
            pastaProntos.mkdir();
        }
		File pastaEnviados = new File("enviados");
        if (!pastaProntos.exists()) {
            pastaProntos.mkdir();
        }
		Scanner scann = new Scanner(System.in);
        System.out.print("Aguardar por arquivos? S/N ");
        String ans = scann.next();
        if (ans.equalsIgnoreCase("S")) {
		
			try {
				ServerSocket serverSocket = new ServerSocket(1234);
         
				System.out.println("Esperando Conexão");
			
				while (true) {
				
				// 2o plano e abertura de socket para diferentes hosts
				Socket socket = serverSocket.accept();
				System.out.println("Conexão estabelecida com " + socket.getInetAddress());	
				Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
				//recebimento de arquivos, criação do stream, buffer, gravação, fechamento da conexão
					try {
                            
                            InputStream inputStream = socket.getInputStream();
							Socket socket = serverSocket.accept();
                            
                            File file = new File("recebidos/" + System.currentTimeMillis() + ".txt");
                            FileOutputStream fileOutputStream = new FileOutputStream(file);

                            
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, bytesRead);
                            }
							fileOutputStream.close();
                            inputStream.close();
                            socket.close();

                            System.out.println("Arquivo recebido e armazenado em disco: " + file.getAbsolutePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
				
					}
				});
				
				thread.start();
				
			}
		
	    } catch (IOException e) {
            e.printStackTrace();
		}
	} else 
			{  
			Scanner scanner = new Scanner(System.in);

        //opções do usuario e algoritmos correlatos
		
        		 
			while (true) {

			
				System.out.println("Escolha uma opção:");
				System.out.println("1 - Ler um arquivo");
				System.out.println("2 - Processar um arquivo");
				System.out.println("3 - Enviar um arquivo");
				String opcao = scanner.nextLine();

				// Opção 1 - Ler um arquivo
				if (opcao.equals("1")) {
					File[] arquivos = pastaRecebidos.listFiles();
					if (arquivos.length == 0) {
						System.out.println("Nenhum arquivo encontrado na pasta \"recebidos\".");
					} else {
						System.out.println("Arquivos disponíveis para leitura:");
						for (int i = 0; i < arquivos.length; i++) {
							System.out.println((i + 1) + " - " + arquivos[i].getName());
						}
						System.out.println("Digite o número do arquivo que deseja ler:");
						String numeroArquivo = scanner.nextLine();
						int indiceArquivo = Integer.parseInt(numeroArquivo) - 1;
						if (indiceArquivo >= 0 && indiceArquivo < arquivos.length) {
							System.out.println("Você escolheu o arquivo \"" + arquivos[indiceArquivo].getName() + "\".");
						} else {
							System.out.println("Opção inválida.");
						}
					}
				}

				// Opção 2 - Processar um arquivo, regras do negocio
				else if (opcao.equals("2")) {
                
				
					File folder = new File("recebidos");
					File[] files = folder.listFiles();
					Scanner scan = new Scanner(System.in);
					int index = 1;
					for (File file : files) {
						if (file.isFile()) {
							System.out.println(index + ". " + file.getName());
						index++;
						}
					}
				System.out.print("Escolha um arquivo: ");	
					int choice = scan.nextInt();
					File selectedFile = files[choice - 1];
					File newFile = new File("prontos/" + selectedFile.getName());
					selectedFile.renameTo(newFile);
					BufferedReader reader = new BufferedReader(new FileReader(newFile));
					String line;
					String[] values = null;
					while ((line = reader.readLine()) != null) {
					values = line.split(";");
					}
					reader.close();
					String pioneiro = values[0];
					int repPioneiro = Integer.parseInt(values[1]);
					String vizinho = values[2];
					int repVizinho = Integer.parseInt(values[3]);
					int nivel = Integer.parseInt(values[4]);
					int salto = Integer.parseInt(values[5]);
					int qualificacao = Integer.parseInt(values[6]);
					int repPostagem = Integer.parseInt(values[7]);
					InetAddress ipLocal = InetAddress.getLocalHost();
					if (ipLocal.getHostAddress().equals(pioneiro)) {
						if (repPostagem <= -3) {
							newFile.renameTo(new File("arquivados/" + newFile.getName()));
						} else {
							nivel *= 2;
						}
					}
					System.out.print("Você gostou da mensagem? (S/N): ");
					String answer = scanner.next();
					int qualificacaoLocal = answer.equalsIgnoreCase("S") ? 1 : -1;
					repPostagem += qualificacaoLocal;
					if (salto == nivel) {
						Socket socket = new Socket(pioneiro, 1234);
						socket.getOutputStream().write(newFile.getName().getBytes());
						socket.close();
						newFile.renameTo(new File("enviados/" + newFile.getName()));
					} else {
						System.out.print("Qual IP deve ser enviado? ");
						String ip = scanner.next();
						InetAddress ipDestino = InetAddress.getByName(ip);
						Socket socket = new Socket(ipDestino, 1234);
						socket.getOutputStream().write(newFile.getName().getBytes());
						socket.close();
					}
					String newLine = pioneiro + ";" + repPioneiro + ";" + ipLocal.getHostAddress() + ";" + nivel + ";" + salto + ";" + qualificacao + ";" + repPostagem;
			
				}
				

				// Opção 3 - Enviar um arquivo
				else if (opcao.equals("3")) {
					File pastaPronto = new File("prontos");
					File[] arquivos = pastaPronto.listFiles();
					if (arquivos.length == 0) {
						System.out.println("Nenhum arquivo encontrado na pasta \"pronto\".");
					} else {
						System.out.println("Arquivos disponíveis para envio:");
						for (int i = 0; i < arquivos.length; i++) {
                        System.out.println((i + 1) + " - " + arquivos[i].getName())	;
						}
						System.out.println("Digite o número do arquivo que deseja enviar:");
						String numeroArquivo = scanner.nextLine();
						int indiceArquivo = Integer.parseInt(numeroArquivo) - 1;
						if (indiceArquivo >= 0 && indiceArquivo < arquivos.length) {
                        System.out.println("Digite o endereço IP para envio:");
							String enderecoIP = scanner.nextLine();
							
						} else {
                        System.out.println("Opção inválida.");
						}
					}
				}

            
			}
		}	
	}
}
