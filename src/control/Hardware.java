package control;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.OutputStream;

public class Hardware
{
	private static Hardware hardware = null;
	private Scent scent;
	private Temperature temperature;
	private Bubbles bubbles;
	private SerialPort serialPort;
	private static final String PORT = "COM3";
	private OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	public static Hardware getInstance()
	{
		if (hardware == null)
			hardware = new Hardware();

		return hardware;
	}

	public Hardware()
	{
		initialize();
	}

	public Bubbles getBubbles()
	{
		return bubbles;
	}

	public void setBubbles(Bubbles bubbles)
	{
		this.bubbles = bubbles;
		switch (bubbles)
		{
		case OFF:
			writeToArduino(0x02);
			break;
		case ON:
			writeToArduino(0x01);
			break;
		default:
			break;
		}
	}

	public Scent getScent()
	{
		return scent;
	}

	public void setScent(Scent scent)
	{
		this.scent = scent;
		switch (scent)
		{
		case DULL:
			writeToArduino(0x24);
			break;
		case FOREST:
			writeToArduino(0x22);
			break;
		case FRESH:
			writeToArduino(0x25);
			break;
		case INCENSE:
			writeToArduino(0x21);
			break;
		case SEA:
			writeToArduino(0x23);
			break;
		default:
			break;
		}
	}

	public Temperature getTemperature()
	{
		return temperature;
	}

	public void setTemperature(Temperature temperature)
	{
		this.temperature = temperature;
		switch (temperature)
		{
		case COLD:
			writeToArduino(0x12);
			break;
		case NORMAL:
			writeToArduino(0x13);
			break;
		case WARM:
			writeToArduino(0x11);
			break;
		default:
			break;
		}
	}

	public void initialize()
	{
		try
		{
			CommPortIdentifier portId = CommPortIdentifier
					.getPortIdentifier(PORT);
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			output = serialPort.getOutputStream();
		} catch (NoSuchPortException npE)
		{
			System.err.println(npE.toString());
		} catch (Exception e)
		{
			System.err.println(e.toString());
		}
	}

	public void writeToArduino(int command)
	{
		try
		{
			output.write(command);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void close()
	{
		if (serialPort != null)
		{
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	@Override
	public void finalize() throws Throwable
	{
		close();
		super.finalize();
	}
}
