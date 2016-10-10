package co.in.kpm.problems.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Ping Pong program with configurable ping first or pong first.
 * 
 * Prints ping/pong alternatingly for configured amount of time and shuts down.
 * 
 * @author Krishna Murthy P Mirajkar
 *
 */
public class PingPong {

  private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

  public PingPong() {
  }

  /**
   * Print ping or pong at the given rate after the specified delay and
   * shut-down after given milli-second.
   * 
   * @param pingFirst
   *          print ping first if true
   * @param initialDelayMillis
   *          start printing after delay
   * @param pingEveryMillis
   *          at the specified rate
   * @param shutDownAfterMillis
   *          run ping-pong print for milli-seconds
   */
  public void play(final boolean pingFirst, int initialDelayMillis, int pingEveryMillis, int shutDownAfterMillis) {
    service.scheduleAtFixedRate(new Runnable() {
      private boolean ping = pingFirst;

      public void run() {
        if (ping) {
          System.out.println("ping");
        } else {
          System.out.println("pong");
        }
        ping = !ping;
      }
    }, initialDelayMillis, pingEveryMillis, TimeUnit.MILLISECONDS);

    shutDownAfterMillis(shutDownAfterMillis + initialDelayMillis);
  }

  private void shutDownAfterMillis(int shutDownAfterSeconds) {
    ScheduledExecutorService shutService = Executors.newSingleThreadScheduledExecutor();
    shutService.schedule(() -> {
      try {
        service.shutdownNow();
      } finally {
        shutService.shutdown();
      }
    }, shutDownAfterSeconds, TimeUnit.MILLISECONDS);
  }

  public static void main(String[] args) {
    PingPong pingPong = new PingPong();

    pingPong.play(false, 0, 1000, 5000);
  }

}
