# monerorpc

A Java interface for Monero wallet and daemon RPC.

## Features

* Async
* Easy to use
* Safe

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

## License

Released under the terms of the MIT license.
See [LICENSE](LICENSE) for more information.
