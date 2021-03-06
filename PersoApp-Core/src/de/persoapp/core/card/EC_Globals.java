/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013, 2014 AGETO Innovation GmbH
 *
 * Authors Christian Kahlo, Ralf Wondratschek
 *
 * All Rights Reserved.
 *
 * Contact: PersoApp, http://www.persoapp.de
 *
 * @version 1.0, 30.07.2013 13:50:47
 *
 *          This file is part of PersoApp.
 *
 *          PersoApp is free software: you can redistribute it and/or modify it
 *          under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation, either version 3 of the
 *          License, or (at your option) any later version.
 *
 *          PersoApp is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details.
 *
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with PersoApp. If not, see
 *          <http://www.gnu.org/licenses/>.
 *
 *          Diese Datei ist Teil von PersoApp.
 *
 *          PersoApp ist Freie Software: Sie können es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          späteren veröffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 *
 *          PersoApp wird in der Hoffnung, dass es nützlich sein wird, aber OHNE
 *          JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License für weitere
 *          Details.
 *
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 *
 */
package de.persoapp.core.card;

import java.math.BigInteger;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/**
 * <p>
 * The EC_Globals contains the curves from RFC 5639 / BSI TR-03111, to
 * construct elliptic curves for the use by the PACE-protocol.
 * </p>
 * 
 * @author Christian Kahlo, Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class EC_Globals {
	
	/**
	 * curves from RFC 5639  / BSI TR-03111
	 * ordering according to TR-03110
	 */
	private static final String[][]	DOMAIN_PARAMETERS	= new String[][] {
														// #0 1024-bit MODP Group with 160-bit Prime Order Subgroup
			null,
			// #1 2048-bit MODP Group with 224-bit Prime Order Subgroup
			null,
			// #2 2048-bit MODP Group with 256-bit Prime Order Subgroup
			null,
			// #3 RFU
			null,
			// #4 RFU
			null,
			// #5 RFU
			null,
			// #6 RFU
			null,
			// #7 RFU
			null,
			// #8 NIST P-192 (secp192r1)
			{ "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFF", // p
			"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFC", // A
			"64210519E59C80E70FA7E9AB72243049FEB8DEECC146B9B1", // B
			"188DA80EB03090F67CBF20EB43A18800F4FF0AFD82FF1012", // Gx
			"07192B95FFC8DA78631011ED6B24CDD573F977A11E794811", // Gy
			"FFFFFFFFFFFFFFFFFFFFFFFF99DEF836146BC9B1B4D22831", // N
			"01" // h
			},
			// #9 BrainpoolP192r1
			null,
			// #10 NIST P-224 (secp224r1)* (no integrated mapping)
			null,
			// #11 BrainpoolP224r1
			{ "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000001", // p
			"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFE", // A
			"B4050A850C04B3ABF54132565044B0B7D7BFD8BA270B39432355FFB4", // B
			"B70E0CBD6BB4BF7F321390B94A03C1D356C21122343280D6115C1D21", // Gx
			"BD376388B5F723FB4C22DFE6CD4375A05A07476444D5819985007E34", // Gy
			"FFFFFFFFFFFFFFFFFFFFFFFFFFFF16A2E0B8F03E13DD29455C5C2A3D", // N
			"01" // h
			},
			// #12 NIST P-256 (secp256r1)
			{ "FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFF", // p
			"FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFC", // A
			"5AC635D8AA3A93E7B3EBBD55769886BC651D06B0CC53B0F63BCE3C3E27D2604B", // B
			"6B17D1F2E12C4247F8BCE6E563A440F277037D812DEB33A0F4A13945D898C296", // Gx
			"4FE342E2FE1A7F9B8EE7EB4A7C0F9E162BCE33576B315ECECBB6406837BF51F5", // Gy
			"FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551", // N
			"01" // h
			},
			// #13 BrainpoolP256r1
			{ "A9FB57DBA1EEA9BC3E660A909D838D726E3BF623D52620282013481D1F6E5377", // p
			"7D5A0975FC2C3057EEF67530417AFFE7FB8055C126DC5C6CE94A4B44F330B5D9", // A
			"26DC5C6CE94A4B44F330B5D9BBD77CBF958416295CF7E1CE6BCCDC18FF8C07B6", // B
			"8BD2AEB9CB7E57CB2C4B482FFC81B7AFB9DE27E1E3BD23C23A4453BD9ACE3262", // Gx
			"547EF835C3DAC4FD97F8461A14611DC9C27745132DED8E545C1D54C72F046997", // Gy
			"A9FB57DBA1EEA9BC3E660A909D838D718C397AA3B561A6F7901E0E82974856A7", // N
			"01" // h
			},
			// #14 BrainpoolP320r1
			{ "D35E472036BC4FB7E13C785ED201E065F98FCFA6F6F40DEF4F92B9EC7893EC28FCD412B1F1B32E27", // p
			"3EE30B568FBAB0F883CCEBD46D3F3BB8A2A73513F5EB79DA66190EB085FFA9F492F375A97D860EB4", // A
			"520883949DFDBC42D3AD198640688A6FE13F41349554B49ACC31DCCD884539816F5EB4AC8FB1F1A6", // B
			"43BD7E9AFB53D8B85289BCC48EE5BFE6F20137D10A087EB6E7871E2A10A599C710AF8D0D39E20611", // Gx
			"14FDD05545EC1CC8AB4093247F77275E0743FFED117182EAA9C77877AAAC6AC7D35245D1692E8EE1", // Gy
			"D35E472036BC4FB7E13C785ED201E065F98FCFA5B68F12A32D482EC7EE8658E98691555B44C59311", // N
			"01" // h
			},
			// #15 NIST P-384 (secp384r1)
			null,
			// #16 BrainpoolP384r1
			{ "8CB91E82A3386D280F5D6F7E50E641DF152F7109ED5456B412B1DA197FB71123ACD3A729901D1A71874700133107EC53", // p
			"7BC382C63D8C150C3C72080ACE05AFA0C2BEA28E4FB22787139165EFBA91F90F8AA5814A503AD4EB04A8C7DD22CE2826", // A
			"04A8C7DD22CE28268B39B55416F0447C2FB77DE107DCD2A62E880EA53EEB62D57CB4390295DBC9943AB78696FA504C11", // B
			"1D1C64F068CF45FFA2A63A81B7C13F6B8847A3E77EF14FE3DB7FCAFE0CBD10E8E826E03436D646AAEF87B2E247D4AF1E", // Gx
			"8ABE1D7520F9C2A45CB1EB8E95CFD55262B70B29FEEC5864E19C054FF99129280E4646217791811142820341263C5315", // Gy 
			"8CB91E82A3386D280F5D6F7E50E641DF152F7109ED5456B31F166E6CAC0425A7CF3AB6AF6B7FC3103B883202E9046565", // N
			"01" // h
			},
			// #17 BrainpoolP512r1
			{
			"AADD9DB8DBE9C48B3FD4E6AE33C9FC07CB308DB3B3C9D20ED6639CCA703308717D4D9B009BC66842AECDA12AE6A380E62881FF2F2D82C68528AA6056583A48F3", // P
			"7830A3318B603B89E2327145AC234CC594CBDD8D3DF91610A83441CAEA9863BC2DED5D5AA8253AA10A2EF1C98B9AC8B57F1117A72BF2C7B9E7C1AC4D77FC94CA", // A
			"3DF91610A83441CAEA9863BC2DED5D5AA8253AA10A2EF1C98B9AC8B57F1117A72BF2C7B9E7C1AC4D77FC94CADC083E67984050B75EBAE5DD2809BD638016F723", // B
			"0481AEE4BDD82ED9645A21322E9C4C6A9385ED9F70B5D916C1B43B62EEF4D0098EFF3B1F78E2D0D48D50D1687B93B97D5F7C6D5047406A5E688B352209BCB9F822", // Gx
			"7DDE385D566332ECC0EABFA9CF7822FDF209F70024A57B1AA000C55B881F8111B2DCDE494A5F485E5BCA4BD88A2763AED1CA2B2FA8F0540678CD1E0F3AD80892", // Gy
			"AADD9DB8DBE9C48B3FD4E6AE33C9FC07CB308DB3B3C9D20ED6639CCA70330870553E5C414CA92619418661197FAC10471DB1D381085DDADDB58796829CA90069", // N
			"01" // h
			}											};

	/**
	 * Returns the {@link ECParameterSpec} according to
	 * the given <em>id</em>.
	 * 
	 * @param id
	 *            - The id of the curve.
	 * @return Returns the {@link ECParameterSpec}.
	 */
	public static ECParameterSpec getCurve(final int id) {
		if (id < DOMAIN_PARAMETERS.length && DOMAIN_PARAMETERS[id] != null) {
			final EllipticCurve curve = new EllipticCurve(new ECFieldFp(new BigInteger(DOMAIN_PARAMETERS[id][0], 16)),
					new BigInteger(DOMAIN_PARAMETERS[id][1], 16), new BigInteger(DOMAIN_PARAMETERS[id][2], 16));

			return new ECParameterSpec(curve, new ECPoint(new BigInteger(DOMAIN_PARAMETERS[id][3], 16), new BigInteger(
					DOMAIN_PARAMETERS[id][4], 16)), new BigInteger(DOMAIN_PARAMETERS[id][5], 16), Integer.valueOf(
					DOMAIN_PARAMETERS[id][6], 16));
		}

		return null;
	}
}
