# monerorpc

A Java interface for Monero wallet and daemon RPC.

## Features

* Async
* Easy to use
* Safe

## Installation

(note: requires Java 11)

### Maven

	<dependency>
		<groupId>uk.offtopica</groupId>
		<artifactId>monerorpc</artifactId>
		<version>0.1.0</version>
	</dependency>

### Gradle

	compile group: 'uk.offtopica', name: 'monerorpc', version: '0.1.0'

## Example

	import uk.offtopica.monerorpc.daemon.MoneroDaemonRpcClient;

	import java.net.URI;

	public class Example {
		public static void main(String[] args) throws Exception {
			MoneroDaemonRpcClient daemon =
				new MoneroDaemonRpcClient(URI.create("http://localhost:18081/json_rpc"));
			System.out.printf("%d\n", daemon.getBlockCount().get());
		}
	};

## Projects Using This Library

* [moneropool](https://www.offtopica.uk/projects/moneropool)

## License

Released under the terms of the MIT license.
See [LICENSE](LICENSE) for more information.
