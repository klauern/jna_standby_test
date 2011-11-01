package klauer.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;



/**
 *
 * @author A03182
 */
public class StandBy {
  // Basically this follows JNA's how-to, but for the PowrProf library
  // Windows API is a good reference for these things:
  // http://msdn.microsoft.com/en-us/library/cc433218(VS.85).aspx
  public interface PowrProf extends Library {

    // PowrProf is where you can send a laptop into StandbyMode
    PowrProf INSTANCE = (PowrProf)
      Native.loadLibrary("powrprof", PowrProf.class);


    // See the MSDN documentation: http://msdn.microsoft.com/en-us/library/aa373201(VS.85).aspx
    // There are a number of possible methods that can be used, but I only
    // need to create interface methods for the ones I want to use.
    boolean SetSuspendState(
        boolean Hibernate,
        boolean ForceCritical,
        boolean DisableWakeEvent);
  }

  public static void main(String[] args) {
    // Folder for where the powrprof.dll is.
    System.setProperty("jna.library.path", "C:\\WINDOWS\\system32");

    PowrProf lib = PowrProf.INSTANCE;
    lib.SetSuspendState(true, false, true);
  }
}
