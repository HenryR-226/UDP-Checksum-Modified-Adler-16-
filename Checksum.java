/**
Modifed Adler-32 checksum
	Cast all char to int, this will be it's ASCII value. Take modulus of this int with 32749. This is the highest 2 byte signed integer that is prime. 
	Return this int as checksum.

	Maximum ASCII value is 255. 255 * 500 (max design packet length) = 127,500. 127,500 % 32749 = 61,979, allowing for 61979 possible checksums reported
	as SHORT.
*/

public class Checksum {
	/**@author Henry Rheault
	Generates Checksum for given message when called.
	
	Cast all char to int, this will be it's ASCII value. Take modulus of this int with 32749. This is the highest 2 byte signed integer that is prime. 
	Return this int as checksum.

	*/
	
	
	short asciiSum = 0;						  
	int asciiSumInt=0;					//Max int is 2,147,000,000, big enough for summation of 500 char
	short checksumVal;
	int checksumValInt;					//ChecksumVal argument but as 4 bytes, cast to 2 as final step
	
	public short getChecksum(byte message[], short packetLength) {
		
		//summate entire message ascii value
													//Total number of chars, cast to short
		short characters[] = new short[packetLength];
		for (short counter=0; counter < packetLength; counter++) {
			characters[counter] = (short) message[counter]; 	//Gets ascii memory value of each char and stores in array as short
				
		}
		for (short counter=0; counter < packetLength; counter++) {
			
			asciiSumInt = asciiSumInt + characters[counter];
		}
		
		checksumValInt = (asciiSumInt % 32749);
		checksumVal = (short) checksumValInt;								//Explicit cast at the end for easier implementation
		return checksumVal;
	}
	
	/**
	 * @author Henry Rheault
	 * Reciever implementation of checksum, input message, checkSum short, and packetLength as short,
	 * confirms or rejects checksum for new packet via boolean
	 */
	
	public boolean checkChecksum(byte message[], char checkSum, short packetLength) {
		//Let caller handle Packet Ack Number
		short characters[] = new short[packetLength];
		for (short counter=0; counter < packetLength; counter++) {
			
			characters[counter] = (short) message[counter]; 	//Gets ascii memory value of each char and stores in array as short
																//Does this for ease of casting
		}
		for (short counter=0; counter < packetLength; counter++) {
			
			asciiSumInt = asciiSumInt + characters[counter];
		}
		checksumValInt= (asciiSumInt % 32749);
		checksumVal= (short) checksumValInt;
		
		if (checkSum /*input*/ == checksumVal /*calculated*/) {						
			return true;
		}
		else {
			return false;
		}
		
	}
}
